package com.example.moviescope.auth.viewModel

import android.content.Intent
import android.util.Log
import android.widget.EditText
import com.example.moviescope.MainActivity
import com.example.moviescope.auth.ForgetPassActivity
import com.example.moviescope.screens.auth.login.LoginActivity

data class LoginActivityViewModel(
    private val loginActivity: LoginActivity,
    var name: String = ""
) {
    private var passWordEditText: EditText? = null

    fun initPassEditText(value: EditText) {
        this.passWordEditText = value
    }

    fun onLoginFormSubmitted() {
        val intent = Intent(loginActivity.applicationContext, MainActivity::class.java)
        loginActivity.startActivity(intent)

        Log.d("LoginActivityViewModel", "LoginForm Name: $name")
        Log.d("LoginActivityViewModel", "LoginForm Pass: ${passWordEditText?.text ?: "N/A"}")
    }

    fun navigateToForgetPassword() {
        Intent(loginActivity.applicationContext, ForgetPassActivity::class.java).apply {
            loginActivity.startActivity(this)
        }
    }
}




