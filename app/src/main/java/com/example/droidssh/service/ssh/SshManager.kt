package com.example.droidssh.service.ssh

import com.example.droidssh.domain.model.ServerConnection
import net.schmizz.sshj.SSHClient
import java.util.concurrent.ConcurrentHashMap

class SshManager {
    private val clients = ConcurrentHashMap<Long, SSHClient>()

    fun getClient(id: Long): SSHClient? = clients[id]

    fun connect(connection: ServerConnection): SSHClient {
        val client = SSHClient().apply {
            addHostKeyVerifier(object : net.schmizz.sshj.transport.verification.HostKeyVerifier {
                override fun verify(hostname: String, port: Int, key: java.security.PublicKey): Boolean = true
            })
            connect(connection.host, connection.port)
            if (connection.password != null) {
                authPassword(connection.username, connection.password)
            }
        }
        clients[connection.id] = client
        return client
    }

    fun disconnect(id: Long) {
        clients[id]?.disconnect()
        clients.remove(id)
    }

    fun isConnected(id: Long): Boolean = clients[id]?.isConnected ?: false
}
