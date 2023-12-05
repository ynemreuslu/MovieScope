package com.example.moviescope

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = getSharedPreferences("Theme", Context.MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getBoolean("dark_light", false)

        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}