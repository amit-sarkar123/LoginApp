package com.example.loginapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.loginapp.R
import com.example.loginapp.databinding.ActivityHomeBinding
import com.example.loginapp.util.SharedPreferencesHelper
import com.example.loginapp.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel
    lateinit var prefs: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        prefs = SharedPreferencesHelper.invoke(this)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.loggedIn.observe(this, Observer {
            if(viewModel.loggedIn.value == false) {
                showToast("Logged Out Successfully")
                prefs.removePreference("username")
                startLoginScreen()
            }
        })

        viewModel.user.observe(this, Observer {
            viewModel.fetchFromUser()
        })
    }

    //Starting the login activity when logout is clicked
    fun startLoginScreen() {
        val createUserIntent = Intent(this, LoginActivity::class.java)
        createUserIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(createUserIntent)
        finish()
    }

    fun showToast(value: String) {
        Toast.makeText(this,""+value, Toast.LENGTH_SHORT).show()
    }

}