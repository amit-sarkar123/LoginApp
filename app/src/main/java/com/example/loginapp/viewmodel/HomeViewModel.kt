package com.example.loginapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.loginapp.model.User
import com.example.loginapp.room.UserRepository
import com.example.loginapp.util.SharedPreferencesHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(application: Application): AndroidViewModel(application) {

    var username = MutableLiveData<String>()
    var loggedIn = MutableLiveData<Boolean>()
    var password = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var gender = MutableLiveData<String>()
    var city = MutableLiveData<String>()
    var age = MutableLiveData<String>()
    var user = MutableLiveData<User>()

    private var repository: UserRepository = UserRepository(application)
    private var prefs: SharedPreferencesHelper = SharedPreferencesHelper.invoke(application)

    init {
        username.value = prefs.getPreference("username")
        CoroutineScope(Dispatchers.Default).launch {
            try {
                user.postValue(repository.getUser(username.value!!)?.get(0))
                loggedIn.postValue(true)
            }catch(e: Exception) {
                println(e.toString())
            }
        }
    }

    fun fetchFromUser() {
        username.value = user.value?.username
        name.value = user.value?.name
        age.value = user.value?.age
        gender.value = user.value?.gender
        city.value = user.value?.city
    }

    fun logoutBtnClicked() {
        loggedIn.value = false
    }

}


