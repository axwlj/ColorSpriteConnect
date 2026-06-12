package com.example.droidssh.service.ssh

import com.example.droidssh.domain.model.ServerConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.transport.verification.PromiscuousVerifier
import java.io.InputStream
import java.io.OutputStream

class SshManager {
    private var client: SSHClient? = null

    suspend fun connect(connection: ServerConnection) = withContext(Dispatchers.IO) {
        client = SSHClient().apply {
            // WARNING: PromiscuousVerifier is insecure for production.
            // Implement proper host key verification for a release build.
            addHostKeyVerifier(PromiscuousVerifier())
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
}

data class ShellStreams(
    val inputStream: InputStream,
    val outputStream: OutputStream
)
