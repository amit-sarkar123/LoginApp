package com.example.loginapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.loginapp.R
import com.example.loginapp.databinding.FragmentLoginBinding
import com.example.loginapp.util.SharedPreferencesHelper
import com.example.loginapp.view.HomeActivity
import com.example.loginapp.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel
    lateinit var prefs: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        prefs = SharedPreferencesHelper.invoke(this.requireContext())

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.loggedIn.observe(this, Observer {
            if(viewModel.loggedIn.value == true) {
                prefs.savePreference("username",viewModel.username.value!!)
                showToast("User loggedIn successfully")
                startHomeScreen()
            }else if(viewModel.loggedIn.value == false) {
                showToast("Wrong Username or Password!!!")
            }
        })

        //Observing to validate data and show error
        viewModel.username.observe(this, Observer {
            binding.apply {
                if(usernameField.length() == 0) {
                    usernameField.setError("Username cannot be Empty!!!")
                }
            }
        })

        viewModel.password.observe(this, Observer {
            binding.apply {
                if(passwordField.length() == 0) {
                    passwordField.setError("Password cannot be Empty!!!")
                }
            }
        })

        return binding.root

    }

    fun startHomeScreen() {
        val createUserIntent = Intent(context, HomeActivity::class.java)
        startActivity(createUserIntent)
        activity?.finish()
    }

    fun showToast(value: String) {
        Toast.makeText(this.context,""+value, Toast.LENGTH_SHORT).show()
    }

}