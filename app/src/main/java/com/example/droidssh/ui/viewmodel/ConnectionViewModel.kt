package com.example.droidssh.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidssh.data.local.ConnectionDao
import com.example.droidssh.domain.model.ServerConnection
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConnectionViewModel(private val connectionDao: ConnectionDao) : ViewModel() {

    val connections: StateFlow<List<ServerConnection>> = connectionDao.getAllConnections()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addConnection(connection: ServerConnection) {
        viewModelScope.launch {
            connectionDao.insertConnection(connection)
        }
    }

    fun deleteConnection(connection: ServerConnection) {
        viewModelScope.launch {
            connectionDao.deleteConnection(connection)
        }
    }
}
