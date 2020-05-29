package com.example.chatify

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class PersonalDataManagementFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.personal_data_manage, rootKey)
    }
}
