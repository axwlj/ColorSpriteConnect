package com.example.droidssh.ui.screens.sftp

import com.example.droidssh.domain.model.FileInfo
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SftpScreen(connectionId: Long) {
    // Mock data for the file list
    val files = listOf(
        FileInfo("assets", true, "4096 bytes", "2023-10-20"),
        FileInfo("index.php", false, "12.5 KB", "2023-10-25"),
        FileInfo("config.json", false, "1.2 KB", "2023-10-24")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("SFTP: Web Server", fontSize = 18.sp)
                        Text("/var/www/html/project", style = MaterialTheme.typography.bodySmall)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Upload, contentDescription = "Upload")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Remote/Local Tab Mockup
            TabRow(selectedTabIndex = 0) {
                Tab(selected = true, onClick = { }, text = { Text("远程") })
                Tab(selected = false, onClick = { }, text = { Text("本地") })
            }

            LazyColumn {
                items(files) { file ->
                    FileListItem(file)
                }
            }

            // Progress Bar Mockup
            TransferProgressBar(fileName = "bundle.js.map", progress = 0.75f)
        }
    }
}

@Composable
fun FileListItem(file: FileInfo) {
    ListItem(
        headlineContent = { Text(file.name) },
        supportingContent = { Text("${file.size} • ${file.lastModified}") },
        leadingContent = {
            Icon(
                imageVector = if (file.isDirectory) Icons.Default.Folder else Icons.Default.Description,
                contentDescription = null,
                tint = if (file.isDirectory) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )
        }
    )
}

@Composable
fun TransferProgressBar(fileName: String, progress: Float) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("正在上传: $fileName", style = MaterialTheme.typography.labelSmall)
                Text("${(progress * 100).toInt()}%", style = MaterialTheme.typography.labelSmall)
            }
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
