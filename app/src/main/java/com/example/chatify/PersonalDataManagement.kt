package com.example.chatify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PersonalDataManagement : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, PersonalDataManagementFragment())
            .commit()
    }
}

// TODO add pop up Confirmation Message for each option
