package com.example.moviescope.service


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo


class AuthServices() {
    private lateinit var auth: FirebaseAuth
    var currentUser: UserInfo? = null

    init {
        auth = FirebaseAuth.getInstance()
      //  auth.addAuthStateListener()
        //auth.signOut()
    }
    // TODO: Singiltion
    // Todo Auth Listener
}