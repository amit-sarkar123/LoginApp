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
import com.example.loginapp.databinding.FragmentSignUpBinding
import com.example.loginapp.util.SharedPreferencesHelper
import com.example.loginapp.view.HomeActivity
import com.example.loginapp.viewmodel.LoginViewModel
import com.example.loginapp.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    lateinit var viewModel: SignUpViewModel
    lateinit var prefs: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        prefs = SharedPreferencesHelper.invoke(this.requireContext())

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.userRegistered.observe(this, Observer {
            if(viewModel.userRegistered.value == true) {
                prefs.savePreference("username",viewModel.username.value!!)
                showToast("User Registered Successfully!!!")
                startHomeScreen()
            }else if(viewModel.userRegistered.value == false){
                showToast("Username already exists!!!")
            }else{
                showToast("Please Enter Valid Data!!!")
            }
        })

        //Observing to validate empty Fields and show error
        viewModel.username.observe(this, Observer {
            binding.apply {
                if(signupUsernameField.length() == 0) {
                    signupUsernameField.setError("Username should not be empty!")
                }
            }
        })

        viewModel.password.observe(this, Observer {
            binding.apply {
                if(signupPasswordField.length() == 0) {
                    signupPasswordField.setError("Password should not be empty!")
                }
            }
        })

        viewModel.name.observe(this, Observer {
            binding.apply {
                if(signupNameField.length() == 0) {
                    signupNameField.setError("Name should not be empty!")
                }
            }
        })

        viewModel.age.observe(this, Observer {
            binding.apply {
                if(signupAgeField.length() == 0) {
                    signupAgeField.setError("Age should not be empty!")
                }
            }
        })

        viewModel.city.observe(this, Observer {
            binding.apply {
                if(signupCityField.length() == 0) {
                    signupCityField.setError("City should not be empty!")
                }
            }
        })

        return binding.root
    }

    //Starting the home activity
    fun startHomeScreen() {
        val createUserIntent = Intent(context, HomeActivity::class.java)
        startActivity(createUserIntent)
        activity?.finish()
    }

    fun showToast(value: String) {
        Toast.makeText(this.context,""+value, Toast.LENGTH_SHORT).show()
    }
}