package com.example.droidssh.service.ssh

import com.example.droidssh.domain.model.ServerConnection
import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.sftp.SFTPClient
import java.io.InputStream
import java.io.OutputStream
import java.util.concurrent.ConcurrentHashMap

class SshManager {
    private val clients = ConcurrentHashMap<Long, SSHClient>()

    fun getClient(id: Long): SSHClient? = clients[id]

    suspend fun connect(connection: ServerConnection): SSHClient = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
        val client = SSHClient().apply {
            addHostKeyVerifier(object : net.schmizz.sshj.transport.verification.HostKeyVerifier {
                override fun verify(hostname: String, port: Int, key: java.security.PublicKey): Boolean = true
                override fun findExistingAlgorithms(hostname: String, port: Int): List<String> = emptyList()
            })
            connect(connection.host, connection.port)
            if (connection.password != null) {
                authPassword(connection.username, connection.password)
            }
        }
        clients[connection.id] = client
        return@withContext client
    }

    fun disconnect(id: Long) {
        clients[id]?.disconnect()
        clients.remove(id)
    }

    fun isConnected(id: Long): Boolean = clients[id]?.isConnected ?: false

    fun getShell(id: Long): ShellStreams? {
        val client = clients[id] ?: return null
        val session = client.startSession()
        session.allocateDefaultPTY()
        val shell = session.startShell()
        return ShellStreams(shell.inputStream, shell.outputStream)
    }

    fun getSftpClient(id: Long): SFTPClient? {
        return clients[id]?.newSFTPClient()
    }
}

data class ShellStreams(
    val inputStream: InputStream,
    val outputStream: OutputStream
)
