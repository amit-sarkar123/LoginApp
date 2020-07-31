package com.example.loginapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loginapp.model.User

@Database(version = 2, entities = [User::class])
abstract class UserDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object {
        @Volatile private var instance: UserDatabase? = null

        fun get(context: Context) : UserDatabase? {
            if( instance == null ){
                synchronized(UserDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "user.db")
                        .build()
                }
            }
            return instance
        }

    }

}