package com.example.droidssh.utils

import com.example.droidssh.domain.model.ServerConnection

object SshConfigParser {

    fun parseConfig(content: String): List<ServerConnection> {
        val connections = mutableListOf<ServerConnection>()
        var currentHost: String? = null
        var hostname: String? = null
        var user: String? = null
        var port: Int = 22

        content.lines().forEach { line ->
            val trimmed = line.trim()
            when {
                trimmed.startsWith("Host ") -> {
                    currentHost = trimmed.substring(5)
                }
                trimmed.startsWith("HostName ") -> {
                    hostname = trimmed.substring(9)
                }
                trimmed.startsWith("User ") -> {
                    user = trimmed.substring(5)
                }
                trimmed.startsWith("Port ") -> {
                    port = trimmed.substring(5).toIntOrNull() ?: 22
                }
            }
            if (currentHost != null && hostname != null && user != null) {
                connections.add(ServerConnection(name = currentHost!!, host = hostname!!, username = user!!, port = port))
                currentHost = null
                hostname = null
                user = null
            }
        }
        return connections
    }
}
