package com.example.droidssh.ui.screens.terminal

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SplitTerminalScreen(connectionId1: Long, connectionId2: Long) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            TerminalScreen(connectionId = connectionId1, isSplit = true)
        }
        Box(modifier = Modifier.weight(1f)) {
            TerminalScreen(connectionId = connectionId2, isSplit = true)
        }
    }
}
