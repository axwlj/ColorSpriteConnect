package com.example.droidssh.ui.screens.terminal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FolderSync
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerminalScreen(
    connectionId: Long,
    onNavigateToSftp: () -> Unit = {}
) {
    var command by remember { mutableStateOf("") }
    val terminalOutput = remember { mutableStateListOf("Last login: Wed Oct 25 10:21:44 2023", "ubuntu@prod:~$ ") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Web Server - Prod", fontSize = 16.sp) },
                actions = {
                    IconButton(onClick = onNavigateToSftp) {
                        Icon(Icons.Default.FolderSync, contentDescription = "SFTP", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
    Column(modifier = Modifier.fillMaxSize().padding(padding).background(Color.Black)) {
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

        // Shortcut Commands Area
        LazyRow(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF1A1A1A)).padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf("top", "tail -f log", "systemctl restart nginx", "ls -la")) { cmd ->
                Surface(
                    color = Color.DarkGray,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.clickable { command = cmd }
                ) {
                    Text(
                        text = cmd,
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        // Virtual Keys (Simplified)
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.DarkGray).padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("ESC", "TAB", "CTRL", "ALT", "FN", "|").forEach { key ->
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(width = 45.dp, height = 35.dp),
                    shape = MaterialTheme.shapes.extraSmall
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
}
