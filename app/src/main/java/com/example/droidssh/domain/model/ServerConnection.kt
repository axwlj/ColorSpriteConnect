package com.example.droidssh.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "connections")
data class ServerConnection(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val host: String,
    val port: Int = 22,
    val username: String,
    val authType: AuthType = AuthType.PASSWORD,
    val password: String? = null,
    val privateKeyPath: String? = null,
    val lastUsed: Long = System.currentTimeMillis()
)

enum class AuthType {
    PASSWORD, KEY, AGENT
}
