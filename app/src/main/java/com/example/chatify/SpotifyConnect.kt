package com.example.chatify

import android.content.Intent.EXTRA_USER
import android.content.pm.PackageInstaller.EXTRA_SESSION
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.spotify_connect.*

class SpotifyConnect : AppCompatActivity() {

    var defaultUser: User = User()
    var defaultSession: Session = Session(defaultUser, defaultUser.premium_priv, false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.spotify_connect)

        defaultUser = intent.extras?.get("user") as User
        defaultSession = intent.extras?.get("session") as Session
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

            confirmLogin(email, password)
        }

        builder.show()

    }

    private fun confirmLogin(email: String, password: String)
    {
        // TODO Query Spotify to Check Credentials
        var valid: Boolean = true                                   // TODO (Demo Only!)
        if (valid)
        {
            setLinkedBool(email)
        }
        else
        {
            val builder = AlertDialog.Builder(this)
            builder.setPositiveButton("OK", null)
            builder.setTitle("Invalid Spotify Info")
            builder.setMessage("Please check your Spotify credentials.")

            builder.show()
        }
    }

    private fun setLinkedBool(email: String)        // TODO Could Change to Username
    {
        if (defaultUser != null) {
            defaultUser.spotify_user = email
        }
        if (defaultSession != null) {
            defaultSession.spot_linked = true
        }
    }

}