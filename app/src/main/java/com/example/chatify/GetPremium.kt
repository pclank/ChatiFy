package com.example.chatify

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat

class GetPremium : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.get_premium)
    }


    fun buyNowButton(view: View) {
        val intent = Intent(this, PaymentActivity::class.java)
        startActivity(intent)
    }
}