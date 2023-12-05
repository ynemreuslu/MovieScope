package com.example.moviescope.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.moviescope.MainActivity
import com.example.moviescope.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUI()
    }

    private fun initializeUI() {
        setupRegisterButton()

    }

    private fun checkPassword(): Boolean {
        return binding.passWordEditText.text.toString() == binding.confirmPasswordEditText.text.toString()
    }

    private fun setupRegisterButton() {
        binding.button.setOnClickListener {
            if (checkPassword()) {
                navigateToMainActivity()
                logPrint()
            } else {
                showPasswordMismatchError()
            }
        }
    }

    private fun showPasswordMismatchError() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("Error")
            .setMessage("Passwords do not match")
            .setPositiveButton("OK") { dialog, which ->
                // Positive button click action, if needed
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun logPrint() {
        Log.d("Register Activity ", "Username : ${binding.usernameEditText.text.toString()}")
        Log.d("Register Activity", " E-mail :  ${binding.emailEditText.text.toString()}")
        Log.d("Register Activity", "Password :  ${binding.passWordEditText.text.toString()}")
    }


}