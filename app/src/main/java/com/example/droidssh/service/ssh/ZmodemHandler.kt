package com.example.droidssh.service.ssh

import java.io.InputStream
import java.io.OutputStream

class ZmodemHandler(
    private val inputStream: InputStream,
    private val outputStream: OutputStream,
    private val onTransferProgress: (Float) -> Unit,
    private val onTransferComplete: () -> Unit
) {
    enum class State { IDLE, WAITING_FOR_ZRQINIT, RECEIVING_DATA, SENDING_DATA }
    private var currentState = State.IDLE

    fun handleLine(line: String) {
        if (line.contains("ZRQINIT")) {
            currentState = State.WAITING_FOR_ZRQINIT
            // 发送 ZACK 响应 (简化版)
            sendZack()
        }
    }

    private fun sendZack() {
        outputStream.write(byteArrayOf(0x2A, 0x2A, 0x18, 0x42, 0x30, 0x30, 0x0D, 0x8A.toByte()))
        outputStream.flush()
    }

    // 实际的二进制流处理需要更底层的字节读取，此处为架构展示
}
