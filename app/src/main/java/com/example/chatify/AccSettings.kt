package com.example.chatify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AccSettings : AppCompatActivity(){
    var defaultUser = User()
    var defaultSession = Session(defaultUser, defaultUser.premium_priv, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, AccSettingsFragment())
            .commit()

        defaultSession = intent.extras?.get("session") as Session
        defaultUser = intent.extras?.get("user") as User
    }

    fun displaySpotify()
    {
        val intent = Intent(this, SpotifyConnect::class.java)
        intent.putExtra("session", defaultSession)                      // Pass Objects to Activity
        intent.putExtra("user", defaultUser)

        startActivity(intent)
    }
}
