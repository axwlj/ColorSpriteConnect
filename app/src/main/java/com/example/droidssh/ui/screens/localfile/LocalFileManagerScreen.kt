package com.example.droidssh.ui.screens.localfile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.droidssh.domain.model.FileInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalFileManagerScreen() {
    val localFiles = listOf(
        FileInfo("Download", true, "--", "2023-11-01"),
        FileInfo("ssh_config", false, "1.5 KB", "2023-10-25"),
        FileInfo("id_rsa.pub", false, "0.4 KB", "2023-10-20")
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("本地文件") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.CreateNewFolder, contentDescription = "New Folder")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(localFiles) { file ->
                ListItem(
                    headlineContent = { Text(file.name) },
                    supportingContent = { Text("${file.size} • ${file.lastModified}") },
                    leadingContent = {
                        Icon(
                            if (file.isDirectory) Icons.Default.Folder else Icons.Default.Description,
                            contentDescription = null,
                            tint = if (file.isDirectory) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                        )
                    }
                )
            }
        }
    }
}
