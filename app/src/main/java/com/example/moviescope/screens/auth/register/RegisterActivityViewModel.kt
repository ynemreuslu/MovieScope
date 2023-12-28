package com.example.moviescope.screens.auth.register

import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescope.R
import com.example.moviescope.service.AuthenticationManager
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class RegisterActivityViewModel : ViewModel() {

    var username: String = ""
    var email: String = ""
    var password: String = ""
    var confirmPassword: String = ""
    var visibilityPassword: Boolean = false
    private var weakReference: WeakReference<RegisterActivity> = WeakReference(RegisterActivity())

    fun onRegisterActivity() {
        if (validateForm(username, email, password, confirmPassword)) {
            if (arePasswordsMatching()) onRegister()
            else showToast(R.string.password_match)
        }
    }

    fun onRegister() {
        viewModelScope.launch {
            AuthenticationManager.register(email, password)
            weakReference.get()?.navigateToLoginActivity()
        }
    }

    fun validateForm(
        username: String, email: String, password: String, confirmPassword: String
    ): Boolean {
        return when {
            username.isEmpty() -> {
                showToast(R.string.username)
                false
            }

            email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showToast(R.string.email_valid)
                false
            }

            password.isEmpty() -> {
                showToast(R.string.password)
                false
            }

            confirmPassword.isEmpty() -> {
                showToast(R.string.confirm_password)
                false
            }

            else -> true
        }
    }

    private fun showToast(messageResId: Int) {
        weakReference.get()?.let {
            Toast.makeText(
                it.applicationContext,
                ContextCompat.getString(it.application, messageResId),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun arePasswordsMatching(): Boolean {
        return password == confirmPassword
    }

    fun onVisibilityPassword() {
        weakReference.get()?.passwordVisibility(visibilityPassword)
    }

    fun setWeakReference(activity: RegisterActivity) {
        weakReference = WeakReference(activity)
    }
}