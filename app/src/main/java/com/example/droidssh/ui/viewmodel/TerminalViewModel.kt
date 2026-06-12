package com.example.droidssh.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidssh.domain.model.ServerConnection
import com.example.droidssh.service.ssh.SshManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TerminalViewModel(private val sshManager: SshManager) : ViewModel() {

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected

    val output = mutableStateListOf<String>()

    fun connect(connection: ServerConnection) {
        viewModelScope.launch {
            try {
                sshManager.connect(connection)
                _isConnected.value = true
                output.add("Connected to ${connection.host}")
                // Start listening to input stream here
            } catch (e: Exception) {
                output.add("Connection failed: ${e.message}")
            }
        }
    }

    fun sendCommand(cmd: String) {
        // Logic to write to SshManager output stream
        output.add("ubuntu@prod:~$ $cmd")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            sshManager.disconnect()
        }
    }
}
