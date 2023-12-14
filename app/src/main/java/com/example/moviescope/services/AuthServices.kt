package com.example.moviescope.service


import android.util.Log
import com.example.moviescope.screens.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import java.lang.ref.WeakReference


object AuthServices {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
    private val mWeakReferences: WeakReference<LoginActivity> = WeakReference(LoginActivity())

    fun loginUser(email: String, password: String, callback: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("Auth Services", "Successfully logged in to the application")
                callback(true)
            } else {
                Log.e("Auth Services", "Failed to log in to the application")
                callback(false)
            }
        }
    }

    fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("Auth Services", "Successfully registered")

            } else {
                Log.e("Auth Services", "Failed to register")

            }
        }
    }


    fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("Auth Services", "Password reset email sent")
            } else {
                Log.e("Auth Services", "Failed to send password reset email")
            }
        }
    }


    fun isUserStart(): Boolean {
        val user = auth.currentUser
        return user != null
    }

    fun isUserLoggedIn() {
        mAuthListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if (user != null) {

                Log.d("Listener", "onAuthStateChanged:signed_in:" + user.uid)
            } else {
                Log.d("Listener", "onAuthStateChanged:signed_out")
            }
        }
        auth.addAuthStateListener(mAuthListener!!)
    }

    fun isUserOut() {
        auth.signOut()
    }

}


