package com.example.droidssh.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.droidssh.ui.screens.connection.ConnectionListScreen
import com.example.droidssh.ui.screens.terminal.TerminalScreen

@Composable
fun AdaptiveMainScreen(windowWidthSizeClass: WindowWidthSizeClass) {
    if (windowWidthSizeClass == WindowWidthSizeClass.Expanded) {
        // Tablet / Large Screen: Dual Column Layout
        Row(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                ConnectionListScreen(onConnect = { })
            }
            Divider(modifier = Modifier.width(1.dp).fillMaxHeight())
            Box(modifier = Modifier.weight(2f)) {
                TerminalScreen(connectionId = 1L)
            }
        }
    } else {
        // Phone: Single Column Layout (handled by NavHost)
        ConnectionListScreen(onConnect = { })
    }
}
