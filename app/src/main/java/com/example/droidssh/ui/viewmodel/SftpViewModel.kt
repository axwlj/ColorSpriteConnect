package com.example.droidssh.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.droidssh.domain.model.FileInfo
import com.example.droidssh.service.ssh.SshManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SftpViewModel @Inject constructor(private val sshManager: SshManager) : ViewModel() {

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

    fun downloadFile(fileName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sftp = sshManager.getSftpClient()
                if (sftp != null) {
                    val remoteFile = if (currentPath == "/") "/$fileName" else "$currentPath/$fileName"
                    // 实现下载逻辑，例如保存到内部存储
                    // sftp.get(remoteFile, localPath)
                    sftp.close()
                }
            } catch (e: Exception) { /* Log error */ }
        }
    }
}
