package com.example.droidssh.utils

import java.net.InetAddress
import java.net.Socket

object NetworkUtils {

    fun ping(host: String): Boolean {
        return try {
            val address = InetAddress.getByName(host)
            address.isReachable(3000)
        } catch (e: Exception) {
            false
        }
    }

    fun scanPort(host: String, port: Int): Boolean {
        return try {
            val socket = Socket()
            socket.connect(java.net.InetSocketAddress(host, port), 1000)
            socket.close()
            true
        } catch (e: Exception) {
            false
        }
    }
}
