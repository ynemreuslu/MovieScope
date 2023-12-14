package com.example.moviescope.screens.auth.login


import android.widget.Toast
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.ViewModel
import com.example.moviescope.R
import com.example.moviescope.service.AuthServices
import java.lang.ref.WeakReference


class LoginActivityViewModel : ViewModel() {

    var email: String = ""
    var password: String = ""
    var visibilityPassWord: Boolean = false
    private var mWeakReference: WeakReference<LoginActivity> = WeakReference(null)

    fun onLoginButton() {
        if (validate(email, password)) {
            AuthServices.loginUser(email, password) { isSuccess ->
                if (isSuccess) mWeakReference.get()?.navigateToMainActivity()
                else showToast(R.string.login_faild)
            }

        }
    }

    fun validate(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                showToast(R.string.plase_email)
                false
            }

            password.isEmpty() -> {
                showToast(R.string.password)
                false
            }

            else -> true
        }
    }

    private fun showToast(messageResId: Int) {
        mWeakReference.get()?.let {
            Toast.makeText(
                it.applicationContext, getString(it.application, messageResId), Toast.LENGTH_LONG
            ).show()
        }
    }

    fun onRegisterClick() {
        mWeakReference.get()?.navigateToRegisterActivity()
    }

    fun onForgetPasswordClick() {
        mWeakReference.get()?.forgetPassword()
    }

    fun onPasswordVisibilityClick() {
        mWeakReference.get()?.showPassword(visibilityPassWord)
    }

    fun onUserStart() {
        if (AuthServices.isUserStart()) mWeakReference.get()?.navigateToMainActivity()


    }

    fun setWeakReference(activity: LoginActivity) {
        mWeakReference = WeakReference(activity)
    }

}