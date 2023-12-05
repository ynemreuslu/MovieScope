package com.example.moviescope.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moviescope.R
import com.example.moviescope.databinding.ActivityForgetPassBinding
import com.google.firebase.auth.FirebaseAuth


class ForgetPassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetPassBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.resetButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()

            if (email.isNotEmpty()) {
                sendPasswordResetEmail(email)
            } else {
                showToast(getString(R.string.plase_email))
            }
        }
    }

    private fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showToast(getString(R.string.reset_email))
            } else {
                showToast(getString(R.string.reset_email_error))
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}