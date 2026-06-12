package com.example.droidssh.service.ssh

import com.example.droidssh.domain.model.ServerConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.sftp.SFTPClient
import net.schmizz.sshj.transport.verification.PromiscuousVerifier
import java.io.InputStream
import java.io.OutputStream

class SshManager {
    private var client: SSHClient? = null

    suspend fun connect(connection: ServerConnection) = withContext(Dispatchers.IO) {
        client = SSHClient().apply {
            // 在实际应用中，这里应该从本地数据库加载已知的 HostKey
            // 这里我们预留一个自定义验证器的逻辑
            addHostKeyVerifier(object : net.schmizz.sshj.transport.verification.HostKeyVerifier {
                override fun verify(hostname: String, port: Int, key: java.security.PublicKey): Boolean {
                    // TODO: 实现提示用户接受新指纹或检查已知指纹的逻辑
                    return true
                }
            })
            connect(connection.host, connection.port)
            if (connection.password != null) {
                authPassword(connection.username, connection.password)
            }
            // Add key auth logic here
        }
    }

    fun getShell(): ShellStreams? {
        val session = client?.startSession() ?: return null
        session.allocateDefaultPTY()
        val shell = session.startShell()
        return ShellStreams(shell.inputStream, shell.outputStream)
    }

    suspend fun disconnect() = withContext(Dispatchers.IO) {
        client?.disconnect()
        client = null
    }

    fun isConnected(): Boolean = client?.isConnected ?: false

    fun getSftpClient(): SFTPClient? {
        return client?.newSFTPClient()
    }
}

data class ShellStreams(
    val inputStream: InputStream,
    val outputStream: OutputStream
)
