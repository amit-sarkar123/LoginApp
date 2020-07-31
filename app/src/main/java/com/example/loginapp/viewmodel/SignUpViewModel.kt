package com.example.loginapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.loginapp.model.User
import com.example.loginapp.room.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class SignUpViewModel(application: Application): AndroidViewModel(application) {

    //variables user for registering user
    var username = MutableLiveData<String>()
    var userRegistered = MutableLiveData<Boolean>()
    var password = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var gender = MutableLiveData<String>()
    var city = MutableLiveData<String>()
    var age = MutableLiveData<String>()
    var user = MutableLiveData<User>()

    private var repository: UserRepository = UserRepository(application)

    fun setGender(value: String) {
        gender.value = value
    }

    fun getUser(): User {
        return User(
            username.value.toString(),
            password.value.toString(),
            name.value.toString(),
            gender.value.toString(),
            age.value.toString(),
            city.value.toString()
        )
    }

    //To check if any field is left empty
    fun invalidData(): Boolean {
        return username.value.isNullOrEmpty() ||
            password.value.isNullOrEmpty() ||
            name.value.isNullOrEmpty() ||
            gender.value.isNullOrEmpty() ||
            age.value.isNullOrEmpty() ||
            city.value.isNullOrEmpty()
    }

    //To validate data and register a user
    fun signupBtnClicked() {
        if(invalidData()) {
            userRegistered.postValue(null)
            return
        }
        CoroutineScope(Dispatchers.Default).launch {
            try {
                var userList = listOf(getUser())
                repository.registerUser(userList)
                userRegistered.postValue(true)
            }catch (e: Exception) {
                userRegistered.postValue(false)
            }
        }
    }

}