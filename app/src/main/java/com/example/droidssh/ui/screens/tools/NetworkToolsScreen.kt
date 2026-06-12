package com.example.droidssh.ui.screens.tools

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkToolsScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("网络工具箱") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ToolCard("Ping 诊断", "检查远程主机的连通性", Icons.Default.NetworkCheck)
            ToolCard("端口扫描", "扫描远程服务器的开放端口", Icons.Default.Search)
            ToolCard("DNS 查询", "解析域名对应的 IP 地址", Icons.Default.Dns)
        }
    }
}

@Composable
fun ToolCard(title: String, desc: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        ListItem(
            headlineContent = { Text(title) },
            supportingContent = { Text(desc) },
            leadingContent = { Icon(icon, contentDescription = null) }
        )
    }
}
