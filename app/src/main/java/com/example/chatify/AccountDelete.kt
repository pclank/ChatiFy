package com.example.chatify

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.account_delete.*


class AccountDelete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_delete)
    }

    fun deleteAccount(view: View) {
        val password = editText2.text.toString()

        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("OK", null)


        if (password.isNotEmpty()) {
            // TODO collect user data
            // TODO delete user data
            // TODO get defaultUser, defaultSession from main

            // main.defaultUser.userDeletion()
            // main.defaultSession.disconnectUser()
            builder.setTitle("Deletion Successful")
            builder.setMessage("Your account and user data have been deleted. " +
                    "You are now disconnected.")
        }
        else {
            builder.setTitle("Empty password")
            builder.setMessage("Please fill in your ChatiFy password " +
                    "in order to verify the account deletion.")
        }

        builder.show()
    }
}