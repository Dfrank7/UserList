package com.oladipo.fairmoneytest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseUsers(
    @PrimaryKey
    val id: String, val lastName: String, val firstName: String,
    val email:String, val title:String
)