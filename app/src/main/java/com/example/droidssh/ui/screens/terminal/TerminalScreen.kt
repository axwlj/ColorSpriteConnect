package com.example.droidssh.ui.screens.terminal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TerminalScreen(connectionId: Long) {
    var command by remember { mutableStateOf("") }
    val terminalOutput = remember { mutableStateListOf("Last login: Wed Oct 25 10:21:44 2023", "ubuntu@prod:~$ ") }

    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // Output Area
        LazyColumn(
            modifier = Modifier.weight(1f).padding(8.dp),
            reverseLayout = false
        ) {
            items(terminalOutput) { line ->
                Text(
                    text = line,
                    color = Color.Green,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp
                )
            }
        }

        // Virtual Keys (Simplified)
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.DarkGray).padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("ESC", "TAB", "CTRL", "ALT", "FN").forEach { key ->
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = 50.dp, height = 30.dp)
                ) {
                    Text(key, fontSize = 10.sp, color = Color.White)
                }
            }
        }

        // Input Field
        TextField(
            value = command,
            onValueChange = { command = it },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            placeholder = { Text("输入命令...", color = Color.Gray) }
        )
    }
}
