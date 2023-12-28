package com.example.moviescope.screens.auth.forget

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.moviescope.R
import com.example.moviescope.service.AuthenticationManager
import java.lang.ref.WeakReference

class ForgetPassViewModel : ViewModel() {

    var email: String = ""
    private var weakReference: WeakReference<ForgetPassActivity?> = WeakReference(null)

    fun onSendPasswordResetEmail() {
        if (email.isNotEmpty()) {
            AuthenticationManager.sendPasswordResetEmail(email)
            showToast(R.string.reset_email)
            weakReference.get()?.navigateToLogin()
        } else {
            showToast(R.string.reset_email_error)
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

    fun setActivity(activity: ForgetPassActivity) {
        weakReference = WeakReference(activity)
    }
}
