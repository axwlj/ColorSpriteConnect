package com.example.droidssh.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.droidssh.domain.model.ServerConnection

@Database(entities = [ServerConnection::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun connectionDao(): ConnectionDao
}
