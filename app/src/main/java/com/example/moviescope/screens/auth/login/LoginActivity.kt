package com.example.moviescope.screens.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.moviescope.MainActivity
import com.example.moviescope.R
import com.example.moviescope.databinding.ActivityLoginBinding
import com.example.moviescope.screens.auth.forget.ForgetPassActivity
import com.example.moviescope.screens.auth.register.RegisterActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this)[LoginActivityViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.setWeakReference(this@LoginActivity)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onUserStart()
    }

    fun navigateToMainActivity() {
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showPassword(isChecked: Boolean) {
        binding.password.inputType =
            if (isChecked) InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    fun navigateToRegisterActivity() {
        val registerActivityIntent = Intent(this, RegisterActivity::class.java)
        startActivity(registerActivityIntent)
    }

    fun forgetPassword() {
        val intent = Intent(this, ForgetPassActivity::class.java)
        startActivity(intent)
    }
}
