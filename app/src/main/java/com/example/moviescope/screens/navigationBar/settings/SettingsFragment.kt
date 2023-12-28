package com.example.moviescope.screens.navigationBar.settings

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.os.LocaleListCompat
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.moviescope.R
import com.example.moviescope.screens.auth.login.LoginActivity
import com.example.moviescope.service.AuthenticationManager


class SettingsFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val darkThemeSwitch =
            findPreference<SwitchPreferenceCompat>("dark_theme_preference_key")

        val themePreferences =
            requireContext().getSharedPreferences("Theme", Context.MODE_PRIVATE)
        val isDarkTheme = themePreferences.getBoolean("dark_light", false)
        val themeEditor = themePreferences.edit()
        darkThemeSwitch?.isChecked = isDarkTheme

        darkThemeSwitch?.setOnPreferenceChangeListener { _, newValue ->
            val isChecked = newValue as Boolean

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                themeEditor.putBoolean("dark_light", true)
                themeEditor.apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                themeEditor.putBoolean("dark_light", false)
                themeEditor.apply()
            }

            true
        }

        val languageListPreference = findPreference<ListPreference>("language_preference_key")

        languageListPreference?.setOnPreferenceChangeListener { _, newValue ->
            val selectedLanguage = newValue as String
            updateLocale(selectedLanguage)
            true
        }

        val logoutPreference = findPreference<Preference>("logout_preference_key")
        logoutPreference?.setOnPreferenceClickListener {
            showLogoutConfirmationDialog()
            true
        }


    }


    private fun updateLocale(language: String) {
        val languagePreferences =
            requireContext().getSharedPreferences("language", Context.MODE_PRIVATE).edit()
        languagePreferences.putString("TR-EN", language)
        languagePreferences.apply()

        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language))
    }

    private fun applyTheme() {
        val themePreferences =
            requireContext().getSharedPreferences("Theme", Context.MODE_PRIVATE)
        val isDarkTheme = themePreferences.getBoolean("dark_light", false)

        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun showLogoutConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.log_sure)
            .setPositiveButton(R.string.yes) { _, _ -> navigateToEntryActivity() }
            .setNegativeButton(R.string.no) { _, _ -> }
            .create()

        alertDialog.setOnShowListener {
            val positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))

            val negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            negativeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
        }

        alertDialog.show()
    }

    private fun navigateToEntryActivity() {
        AuthenticationManager.logout()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
    }
}