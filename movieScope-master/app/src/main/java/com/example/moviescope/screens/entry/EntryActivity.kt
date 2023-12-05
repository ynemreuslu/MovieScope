package com.example.moviescope.screens.entry

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviescope.auth.RegisterActivity
import com.example.moviescope.databinding.ActivityEntryBinding
import com.example.moviescope.screens.auth.login.LoginActivity

class EntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUI()
    }

    private fun initializeUI() {
        setupLoginButton()
        setupRegisterButton()
    }

    private fun setupLoginButton() {
        binding.loginButton.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        Intent(this, LoginActivity::class.java).apply {
            startActivity(this)
        }
    }

    private fun setupRegisterButton() {
        binding.registerButton.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun navigateToRegister() {
        Intent(this, RegisterActivity::class.java).apply {
            startActivity(this)
        }
    }
}