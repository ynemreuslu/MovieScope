package com.example.moviescope.screens.navigationBar.settings

import androidx.preference.Preference
import androidx.preference.PreferenceDataStore

class DataStore : PreferenceDataStore() {
    override fun putString(key: String?, value: String?) {
        super.putString(key, value)
    }

    override fun getString(key: String?, defValue: String?): String? {
        return super.getString(key, defValue)
    }
}