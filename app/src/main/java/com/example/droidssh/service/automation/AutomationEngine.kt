package com.example.droidssh.service.automation

import com.example.droidssh.ui.viewmodel.TerminalViewModel
import kotlinx.coroutines.delay

class AutomationEngine(private val terminalViewModel: TerminalViewModel) {

    suspend fun executeMacro(commands: List<String>) {
        commands.forEach { cmd ->
            terminalViewModel.sendCommand(cmd)
            delay(500) // 等待命令执行间隔
        }
    }
}
