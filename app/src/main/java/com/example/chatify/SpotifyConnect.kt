package com.example.chatify

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.spotify_connect.*

class SpotifyConnect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spotify_connect)
    }

    fun submitSpotifyInfo(view: View) {
        val email = spotify_email_input.text.toString()
        val password = spotify_password_input.text.toString()

        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("OK", null)

        if (email.isEmpty() || password.isEmpty()) {
            builder.setTitle("Invalid Spotify Info")
            builder.setMessage("Please check your Spotify credentials.")
        }
        else {
            // TODO enable spotify features
            builder.setTitle("Login successful")
            builder.setMessage("Spotify features are now accessible inside ChatiFy.")
        }

        builder.show()
    }
}