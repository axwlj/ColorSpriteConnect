package com.example.droidssh.ui.screens.connection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionListScreen(onConnect: (Long) -> Unit) {
    Scaffold(
        topBar = {
            LargeTopAppBar(title = { Text("会话管理") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Add connection */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            // Mock data for prototype implementation
            item {
                ConnectionItem(
                    name = "Web Server - Prod",
                    host = "192.168.1.105",
                    onClick = { onConnect(1) }
                )
            }
        }
    }
}

@Composable
fun ConnectionItem(name: String, host: String, onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text(name) },
        supportingContent = { Text(host) },
        leadingContent = {
            Icon(Icons.Default.Storage, contentDescription = null)
        },
        modifier = Modifier.padding(8.dp),
        tonalElevation = 2.dp,
        shadowElevation = 2.dp
    )
}
