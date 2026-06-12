package com.example.droidssh.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidssh.service.ssh.SshManager
import com.example.droidssh.ui.screens.sftp.FileInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SftpViewModel(private val sshManager: SshManager) : ViewModel() {

    val files = mutableStateListOf<FileInfo>()
    private var currentPath = "/"

    fun loadFiles(path: String = currentPath) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sftp = sshManager.getSftpClient()
                if (sftp != null) {
                    val remoteFiles = sftp.ls(path)
                    withContext(Dispatchers.Main) {
                        files.clear()
                        remoteFiles.forEach { entry ->
                            files.add(
                                FileInfo(
                                    name = entry.name,
                                    isDirectory = entry.isDirectory,
                                    size = "${entry.attributes.size} bytes",
                                    lastModified = entry.attributes.mtime.toString()
                                )
                            )
                        }
                        currentPath = path
                    }
                    sftp.close()
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun navigateTo(folderName: String) {
        val newPath = if (currentPath == "/") "/$folderName" else "$currentPath/$folderName"
        loadFiles(newPath)
    }
}
