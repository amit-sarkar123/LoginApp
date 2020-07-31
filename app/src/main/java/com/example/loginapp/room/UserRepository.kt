package com.example.loginapp.room

import android.app.Application
import android.content.Context
import com.example.loginapp.model.User
import com.example.loginapp.util.SharedPreferencesHelper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserRepository(application: Application): CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var userDao: UserDao?

    init {
        val db = UserDatabase.get(application)
        userDao = db?.UserDao()
    }

    //Fetching user data by username
    fun getUser(username: String) = userDao?.getUser(username)

    //Fetching all users from database
    fun getAllUser() = userDao?.getAllUser()

    //Deleting all users from database
    fun dropDatabase() = userDao?.dropDatabase()

    //Inserting a new user to database
    fun registerUser(user: List<User>) = userDao?.insertUser(user)
}