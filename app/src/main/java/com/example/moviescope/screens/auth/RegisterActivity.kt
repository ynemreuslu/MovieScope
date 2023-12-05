package com.example.moviescope.auth

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.moviescope.MainActivity
import com.example.moviescope.R
import com.example.moviescope.databinding.ActivityRegisterBinding
import com.example.moviescope.screens.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        setupPasswordVisibility()

        binding.registerButton.setOnClickListener {
            val username = binding.username.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            if (validateForm(username, email, password, confirmPassword)) {
                createUserWithEmailAndPassword(email, password)
            }
        }
    }

    private fun setupPasswordVisibility() {
        binding.passwordVisibility.setOnCheckedChangeListener { _, isChecked ->
            passwordVisibility(isChecked)
        }
    }

    private fun passwordVisibility(isChecked: Boolean) {
        binding.password.inputType =
            if (isChecked) InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    private fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (password == binding.confirmPassword.text.toString()) {
                if (task.isSuccessful) {
                    navigateToLoginActivity()
                } else {
                    showErrorToast(getString(R.string.register_faild))
                }
            } else {
                showErrorToast(getString(R.string.password_match))
            }
        }
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun validateForm(
        username: String, email: String, password: String, confirmPassword: String
    ): Boolean {
        return when {
            username.isEmpty() -> {
                showFieldError(binding.username, getString(R.string.username))
                false
            }

            email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showFieldError(binding.email, getString(R.string.email_valid))
                false
            }

            password.isEmpty() -> {
                showFieldError(binding.password, getString(R.string.password))
                false
            }

            confirmPassword.isEmpty() -> {
                showFieldError(binding.confirmPassword, getString(R.string.confirm_password))
                false
            }

            else -> true
        }
    }

    private fun showFieldError(editText: EditText, errorMessage: String) {
        editText.error = errorMessage
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


}
