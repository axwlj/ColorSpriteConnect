package com.example.droidssh.domain.model

data class FileInfo(
    val name: String,
    val isDirectory: Boolean,
    val size: String,
    val lastModified: String
)
