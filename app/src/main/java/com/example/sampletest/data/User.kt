package com.example.sampletest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client_table")
data class User (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val firstName: String,
        val lastName: String,
        val age: Int,
        val gender: String,
        val city: String
    )