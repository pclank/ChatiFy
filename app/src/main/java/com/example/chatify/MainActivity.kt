package com.example.chatify

import android.app.Activity
import android.app.ProgressDialog.show
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import java.time.Instant
import java.time.format.DateTimeFormatter


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

    fun accountSettings(item: MenuItem) {
        val intent = Intent(this, AccSettings::class.java)
        startActivity(intent)
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

    fun hideKeyboardFrom(                                                   // Hide Soft-Keyboard in Fragment
        context: Context,
        view: View
    )
    {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // Chat Object For Presentation
    // @RequiresApi(Build.VERSION_CODES.O)
    private val timestamp: String = "PLACEHOLDER"

    private val ch1 = Chat("song", (0..49).random(), timestamp)                // Song
    private val ch2 = Chat("album", (0..49).random(), timestamp)                // Album
    private val ch3 = Chat("artist", (0..49).random(), timestamp)                // Artist
    private val ch4 = Chat("genre", (0..49).random(), timestamp)                // Genre

    // Fragment Identification Helper Functions
    fun songClick(view: View)
    {
        sendMessage(view, ch1)
    }

    fun albumClick(view: View)
    {
        sendMessage(view, ch2)
    }

    fun artistClick(view: View)
    {
        sendMessage(view, ch3)
    }

    fun genreClick(view: View)
    {
        sendMessage(view, ch4)
    }

    private fun sendMessage(view: View, chat: Chat)                                                // Tested for Album Chat Text-Box
    {
        val textView: TextView = findViewById<TextView>(R.id.editText)
        val msg = textView.text.toString()

        itemListener()

        hideKeyboard(this)

        if (msg.isNotEmpty() && msg.length < 200)                               // Not Empty, 200 Character Limit
        {
            val chatMessage: ChatMessage = ChatMessage("out")                            // Create ChatMessage Object
            chatMessage.setTxt(msg)

            chat.messageArray.add(chatMessage)

            publishMessage(chat.messageArray, chat)

            textView.text = ""                                                      // Clear Input Text-Box

            // TODO DEMO ONLY
            messageDemonstrator(chat)
        }
    }

    private fun publishMessage(msg: ArrayList<ChatMessage>, chat: Chat)
    {
        listView = findViewById(R.id.messages_view)
        val adapter = MessageAdapter(this, msg)
        listView.adapter = adapter
    }

    private fun itemListener()
    {
        // ChatMessage-Item LongClick-Listener Section
        val lv: ListView = findViewById(R.id.messages_view)
        lv.onItemLongClickListener =
            OnItemLongClickListener { arg0, arg1, pos, id ->
                // Debug Purposes: Toast.makeText(this, "Hello World$pos", Toast.LENGTH_LONG).show()
                itemDialog(id)
                true
            }
    }

    private fun itemDialog(id: Long)
    {
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("Flag Message")
            setMessage("Flag Message For Inappropriate Content?")

            builder.setPositiveButton("Yes"){dialogInterface, which ->
                val result: Boolean = flagMessage()

                if (result)
                {
                    Snackbar.make(
                        findViewById(R.id.messages_view),
                        "Thank You For Your Report",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                else
                {
                    Snackbar.make(
                        findViewById(R.id.messages_view),
                        "Report Failed",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

            }

            builder.setNegativeButton("No"){dialogInterface, which ->
                Snackbar.make(
                    findViewById(R.id.messages_view),
                    "Operation Canceled",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            show()
        }
    }

    private fun flagMessage(): Boolean                                      // Message Reporter
    {
        // TODO Add Flagging Functionality
        // TODO Change Message to Removed Form
        return true
    }

    private fun messageDemonstrator(chat: Chat)                                        // Randomly Create Incoming-Messages
    {
        val rand: Int = (0..99).random()
        val ch: ChatMessage = ChatMessage("in")

        if (rand < 10)
        {
            ch.setTxt("I don't like the new album.")
        }
        else if (rand == 10 && rand < 40)
        {
            ch.setTxt("Listening to this makes gives me Goosebumps!")
        }
        else if (rand == 40 && rand < 60)
        {
            ch.setTxt("Check out What The Dead Men Say!")
        }
        else if (rand == 60 && rand < 80)
        {
            ch.setTxt("I love Djent!!!")
        }
        else
        {
            ch.setTxt("Nice!")
        }

        chat.messageArray.add(ch)
        publishMessage(chat.messageArray, chat)
    }

}
