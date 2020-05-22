package com.example.chatify

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class AccSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.acc_settings, rootKey)
    }
}
