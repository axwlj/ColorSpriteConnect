package com.example.droidssh.service.ssh

import com.example.droidssh.domain.model.ServerConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.schmizz.sshj.SSHClient

class TunnelManager(private val client: SSHClient) {

    suspend fun startLocalForwarding(localPort: Int, remoteHost: String, remotePort: Int) = withContext(Dispatchers.IO) {
        // Implementation for local port forwarding
        // client.newLocalPortForwarder(...)
    }
}
