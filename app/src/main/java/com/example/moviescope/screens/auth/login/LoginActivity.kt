package com.example.moviescope.screens.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.moviescope.MainActivity
import com.example.moviescope.R
import com.example.moviescope.auth.ForgetPassActivity
import com.example.moviescope.auth.RegisterActivity
import com.example.moviescope.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            loginUser()
        }

        binding.register.setOnClickListener {
            navigateToRegisterActivity()
        }
        binding.forgetPass.setOnClickListener {
            forgetPassword()
        }
        passwordVisibility()

    }

    private fun loginUser() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        if (validate(email, password)) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navigateToMainActivity()
                } else {
                    showErrorToast(getString(R.string.login_faild))
                }
            }
        }
    }

    private fun validate(email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showFieldError(binding.email, getString(R.string.email_valid))
                false
            }

            TextUtils.isEmpty(password) -> {
                showFieldError(binding.password, getString(R.string.password))
                false
            }

            else -> true
        }
    }

    private fun navigateToMainActivity() {
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }

    private fun navigateToRegisterActivity() {
        val registerActivityIntent = Intent(this, RegisterActivity::class.java)
        startActivity(registerActivityIntent)
    }

    private fun showFieldError(editText: EditText, errorMessage: String) {
        editText.error = errorMessage
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showPassword(isChecked: Boolean) {
        binding.password.inputType =
            if (isChecked) InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }


    private fun passwordVisibility() {
        binding.passwordVisibility.setOnCheckedChangeListener { _, isChecked ->
            showPassword(isChecked)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToMainActivity()
        }

    }

    private fun forgetPassword() {
        val intent = Intent(this, ForgetPassActivity::class.java)
        startActivity(intent)
    }
}