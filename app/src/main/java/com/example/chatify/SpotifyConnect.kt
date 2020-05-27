package com.example.chatify

import android.app.Activity
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.content.pm.PackageInstaller.EXTRA_SESSION
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
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

        hideKeyboard(this)

        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("OK") { dialogInterface, which ->
            view.let {
                Snackbar.make(
                    it,
                    "You Have Successfully Connected",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            confirmLogin(email, password)
        }

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
        defaultUser.spotify_user = email
        defaultSession.spot_linked = true

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("session", defaultSession)                      // Pass Objects to Activity
        intent.putExtra("user", defaultUser)

        Handler().postDelayed({
            startActivity(intent)
        }, 3000)
    }

    private fun hideKeyboard(activity: Activity)                                    // Hide Soft-Keyboard in Activity
    {
        val imm =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}