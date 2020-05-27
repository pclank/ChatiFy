package com.example.chatify

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class AccSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.acc_settings, rootKey)

        (findPreference("connect_spotify") as Preference?)?.onPreferenceClickListener = Preference.OnPreferenceClickListener {

            (activity as AccSettings).displaySpotify()
            true
        }
    }


}
