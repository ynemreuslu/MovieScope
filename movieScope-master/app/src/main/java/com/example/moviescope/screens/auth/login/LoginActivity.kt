package com.example.moviescope.screens.auth.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.example.moviescope.R
import com.example.moviescope.auth.viewModel.LoginActivityViewModel
import com.example.moviescope.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginActivityViewModel: LoginActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginActivityViewModel = LoginActivityViewModel(this)
        binding.viewModel = loginActivityViewModel
        loginActivityViewModel.name = binding.viewModel!!.name
        loginActivityViewModel.initPassEditText(binding.passEditText)

    }

    fun onLoginButtonClick() {
        binding.viewModel?.onLoginFormSubmitted()

    }

    fun onForgetTextClick() {
        binding.viewModel?.navigateToForgetPassword()
    }
}