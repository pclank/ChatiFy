package com.example.chatify

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ListView
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
    private lateinit var listView : ListView

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

    fun hideKeyboard(activity: Activity)                                    // Hide Soft-Keyboard in Activity
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

    fun hideKeyboardFrom(                                                   // Hide Soft-Keyboard in Fragment
        context: Context,
        view: View
    )
    {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    val ch1 = Chat()

    public fun sendMessage(view: View)                                                // Tested for Album Chat Text-Box
    {
        val textView: TextView = findViewById<TextView>(R.id.editText)
        val msg = textView.text.toString()

        hideKeyboard(this)

        if (msg.isNotEmpty() && msg.length < 200)                               // Not Empty, 200 Character Limit
        {
            val chatMessage: ChatMessage = ChatMessage()                            // Create ChatMessage Object
            chatMessage.setTxt(msg)

            ch1.message_array.add(chatMessage)

            publishMessage(ch1.message_array)

            textView.text = ""                                                      // Clear Input Text-Box
        }
    }

    private fun publishMessage(msg: ArrayList<ChatMessage>)
    {
        listView = findViewById(R.id.messages_view)
        val adapter = MessageAdapter(this, msg)
        listView.adapter = adapter
    }

}
