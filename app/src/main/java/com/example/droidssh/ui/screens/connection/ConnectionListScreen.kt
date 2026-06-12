package com.example.droidssh.ui.screens.connection

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionListScreen(
    onConnect: (Long) -> Unit,
    onNavigateToKeys: () -> Unit = {},
    onAddConnection: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(title = { Text("会话管理") })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    label = { Text("列表") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToKeys,
                    icon = { Icon(Icons.Default.Key, contentDescription = null) },
                    label = { Text("密钥") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("设置") }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddConnection) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionItem(name: String, host: String, onClick: () -> Unit) {
    SwipeToDismissBox(
        state = rememberSwipeToDismissBoxState(),
        backgroundContent = {
            Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.errorContainer))
        },
        content = {
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
    )
}
