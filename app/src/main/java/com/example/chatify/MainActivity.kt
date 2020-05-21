package com.example.chatify

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.songChatFragment, R.id.albumChatFragment, R.id.artistChatFragment, R.id.genreChatFragment, R.id.GetPremium, R.id.appSettingsActivity, R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun settingsButton(view: View)                    //Settings Button Press
    {
        val intent = Intent(this, AppSettingsActivity::class.java)

        startActivity(intent)
    }

    private fun checkForPremiumPackages(): Array<String> {
        val foundPackages = arrayOf("package1", "package2", "package3");
        return foundPackages
    }

    fun getPremiumButton(item: MenuItem) {

        val premiumPackages = checkForPremiumPackages();

        if (premiumPackages.isNotEmpty()) {
            val intent = Intent(this, GetPremium::class.java)
            startActivity(intent)
        }
    }

    public fun sendMessage(view: View)                                                // Tested for Album Chat Text-Box
    {
        val textView: TextView = findViewById<TextView>(R.id.editText)
        val msg = textView.text.toString()

        if (msg.isNotEmpty() && msg.length < 200)                               // Not Empty, 200 Character Limit
        {
            // TODO publish() Function
            // publishMessage(msg)

            textView.text = ""                                                      // Clear Input Text-Box
        }
    }

}
