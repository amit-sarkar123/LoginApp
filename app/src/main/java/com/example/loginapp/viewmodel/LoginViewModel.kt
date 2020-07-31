package com.example.loginapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.loginapp.room.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

public class LoginViewModel(application: Application): AndroidViewModel(application) {

    //variables used during login
    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    var loggedIn = MutableLiveData<Boolean>() //it is observed to call home activity

    private var repository: UserRepository = UserRepository(application)

    //To check login username and password and go to home activity
    fun loginBtnClicked() {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val user = repository.getUser(username.value!!)?.get(0)
                if(user?.password == password.value) {
                    loggedIn.postValue(true)
                }else{
                    loggedIn.postValue(false)
                }
            }catch (e: Exception) {
                loggedIn.postValue(false)
            }
        }

    }

}