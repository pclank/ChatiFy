package com.example.chatify

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class AppSettingsFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.app_settings, rootKey)
    }
}

//TODO add functionality to switches