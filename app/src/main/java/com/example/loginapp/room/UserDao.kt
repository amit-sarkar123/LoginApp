package com.example.loginapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.loginapp.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: List<User>)

    @Query("select * from User where username = :username")
    fun getUser(username: String) : List<User>

    @Query("select * from User")
    fun getAllUser() : List<User>

    @Query("delete from User")
    fun dropDatabase()
}