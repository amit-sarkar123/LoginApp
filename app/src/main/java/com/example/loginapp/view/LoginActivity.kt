package com.example.loginapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.loginapp.R
import com.example.loginapp.fragments.LoginFragment
import com.example.loginapp.fragments.SignUpFragment

class LoginActivity : AppCompatActivity() {

    //Fragment Manager to load login and signup fragments
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //initially loading the login fragment
        showLoginFragment()
    }

    //To load the login fragment
    fun showLoginFragment() {
        val transaction = manager.beginTransaction()
        val fragment = LoginFragment()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.commit()
    }

    //To load the signup fragment
    fun showSignUpFragment(view: View) {
        val transaction = manager.beginTransaction()
        val fragment = SignUpFragment()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    fun showToast(value: String) {
        Toast.makeText(this,""+value,Toast.LENGTH_SHORT).show()
    }

}