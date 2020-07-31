package com.example.loginapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User (
    @PrimaryKey val username: String,
    val password: String,
    val name: String,
    val gender: String,
    val age: String,
    val city: String
)