package com.example.chatify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AppSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, AppSettingsFragment())
            .commit()
    }
}