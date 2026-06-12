package com.example.droidssh.data.local

import androidx.room.*
import com.example.droidssh.domain.model.ServerConnection
import kotlinx.coroutines.flow.Flow

@Dao
interface ConnectionDao {
    @Query("SELECT * FROM connections ORDER BY lastUsed DESC")
    fun getAllConnections(): Flow<List<ServerConnection>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConnection(connection: ServerConnection)

    @Delete
    suspend fun deleteConnection(connection: ServerConnection)

    @Query("SELECT * FROM connections WHERE id = :id")
    suspend fun getConnectionById(id: Long): ServerConnection?
}
