package com.example.loginapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPreferencesHelper {

    companion object {

        private var prefs: SharedPreferences? = null

        @Volatile private var instance: SharedPreferencesHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): SharedPreferencesHelper = instance ?: synchronized(LOCK) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }

        private fun buildHelper(context: Context): SharedPreferencesHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }

    }

    //To store a data in SharedPreference
    fun savePreference(key: String, value: String) {
        prefs!!.edit().putString(
            key,
            value
        ).apply()
    }

    //To fetch the stored data
    fun getPreference(key: String): String? {
        return prefs?.getString(key, null)
    }

    //To remove a stored data
    fun removePreference(key: String) {
        prefs!!.edit().remove(key).apply()
    }

}