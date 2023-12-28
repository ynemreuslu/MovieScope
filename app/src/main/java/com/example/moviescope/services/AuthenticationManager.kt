package com.example.moviescope.service


import android.util.Log
import com.google.firebase.auth.FirebaseAuth

object AuthenticationManager {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var authStateListener: FirebaseAuth.AuthStateListener? = null

    fun login(email: String, password: String, onLoginResult: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("AuthenticationManager", "Successfully logged in to the application")
                onLoginResult(true)
            } else {
                Log.e("AuthenticationManager", "Failed to log in to the application")
                onLoginResult(false)
            }
        }
    }

    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("AuthenticationManager", "Successfully registered")
            } else {
                Log.e("AuthenticationManager", "Failed to register")
            }
        }
    }

    fun sendPasswordResetEmail(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("AuthenticationManager", "Password reset email sent")
            } else {
                Log.e("AuthenticationManager", "Failed to send password reset email")
            }
        }
    }

    fun userLoggedIn() {
        authStateListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if (user != null) {
                Log.d("AuthenticationManager", "User logged in: ${user.uid}")
            } else {
                Log.d("AuthenticationManager", "User logged out")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener!!)
    }

    fun isUserStart(): Boolean {
        val user = firebaseAuth.currentUser
        return user != null
    }

    fun logout() {
        firebaseAuth.signOut()
    }
}
