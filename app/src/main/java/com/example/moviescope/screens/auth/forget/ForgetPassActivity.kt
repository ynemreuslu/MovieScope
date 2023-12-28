package com.example.moviescope.screens.auth.forget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.moviescope.databinding.ActivityForgetPassBinding
import com.example.moviescope.screens.auth.login.LoginActivity

class ForgetPassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetPassBinding
    private lateinit var viewModel: ForgetPassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgetPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ForgetPassViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.setActivity(this)
    }

    fun navigateToLogin() {
        Intent(this, LoginActivity::class.java).apply {
            startActivity(this)
        }
    }
}
