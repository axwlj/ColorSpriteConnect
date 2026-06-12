package com.example.droidssh.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Maximize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskSwitcherScreen(onSelectTask: (Long) -> Unit) {
    // Mock list of active sessions
    val activeTasks = listOf(
        TaskInfo(1, "Web Server - Prod", "ubuntu@prod:~$ top"),
        TaskInfo(2, "Home Pi", "pi@raspberrypi:~$ ls -la")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("当前运行的任务", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(activeTasks) { task ->
                TaskCard(task, onSelectTask)
            }
        }
    }
}

@Composable
fun TaskCard(task: TaskInfo, onSelect: (Long) -> Unit) {
    Card(
        modifier = Modifier.size(width = 160.dp, height = 240.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Preview Area
            Box(modifier = Modifier.weight(1f).fillMaxWidth().background(Color.Black).padding(4.dp)) {
                Text(
                    task.lastOutput,
                    color = Color.Green,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 8.sp,
                    lineHeight = 10.sp
                )
            }
            // Title Bar
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(task.name, fontSize = 10.sp, maxLines = 1)
                IconButton(onClick = { onSelect(task.id) }, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Maximize, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

data class TaskInfo(val id: Long, val name: String, val lastOutput: String)
