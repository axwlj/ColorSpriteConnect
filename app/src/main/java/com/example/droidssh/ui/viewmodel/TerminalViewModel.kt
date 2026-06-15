package com.example.droidssh.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidssh.domain.model.ServerConnection
import com.example.droidssh.service.ssh.SshManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

@HiltViewModel
class TerminalViewModel @Inject constructor(private val sshManager: SshManager) : ViewModel() {

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected

    val output = mutableStateListOf<String>()
    private var outputStream: java.io.OutputStream? = null

    fun connect(connection: ServerConnection) {
        viewModelScope.launch {
            try {
                sshManager.connect(connection)
                val streams = sshManager.getShell()
                if (streams != null) {
                    outputStream = streams.outputStream
                    _isConnected.value = true
                    startReading(streams.inputStream)
                }
            } catch (e: Exception) {
                output.add("Connection failed: ${e.message}")
            }
        }
    }

    private val MAX_LINES = 1000

    private fun startReading(inputStream: java.io.InputStream) {
        viewModelScope.launch(Dispatchers.IO) {
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            try {
                while (reader.readLine().also { line = it } != null) {
                    // 检测 Zmodem 握手信号 (rz/sz)
                    if (line?.contains("ZRQINIT") == true || line?.contains("rz waiting") == true) {
                        withContext(Dispatchers.Main) {
                            output.add("检测到 Zmodem 传输请求...")
                        }
                    }

                    withContext(Dispatchers.Main) {
                        if (output.size >= MAX_LINES) {
                            output.removeAt(0)
                        }
                        output.add(line ?: "")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    output.add("Stream closed: ${e.message}")
                }
            }
        }
    }

    fun sendCommand(cmd: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                outputStream?.let {
                    it.write((cmd + "\n").toByteArray())
                    it.flush()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    output.add("Failed to send command: ${e.message}")
                }
            }
        }
    }

    private var currentId: Long? = null

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            currentId?.let { sshManager.disconnect(it) }
        }
    }
}
