package com.example.droidssh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.droidssh.ui.screens.connection.AddConnectionScreen
import com.example.droidssh.ui.screens.connection.ConnectionListScreen
import com.example.droidssh.ui.screens.key.KeyManagementScreen
import com.example.droidssh.ui.screens.sftp.SftpScreen
import com.example.droidssh.ui.screens.terminal.TerminalScreen
import com.example.droidssh.ui.theme.DroidSSHTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DroidSSHTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "connection_list") {
        composable("connection_list") {
            ConnectionListScreen(
                onConnect = { id ->
                    navController.navigate("terminal/$id")
                },
                onNavigateToKeys = {
                    navController.navigate("keys")
                },
                onAddConnection = {
                    navController.navigate("add_connection")
                }
            )
        }
        composable("add_connection") {
            AddConnectionScreen(onBack = { navController.popBackStack() })
        }
        composable("keys") {
            KeyManagementScreen(onBack = { navController.popBackStack() })
        }
        composable("terminal/{connectionId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("connectionId")?.toLong() ?: 0L
            TerminalScreen(
                connectionId = id,
                onNavigateToSftp = { navController.navigate("sftp/$id") }
            )
        }
        composable("sftp/{connectionId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("connectionId")?.toLong() ?: 0L
            SftpScreen(connectionId = id)
        }
    }
}
