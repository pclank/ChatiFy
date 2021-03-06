package com.example.chatify

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.content.pm.PackageInstaller.EXTRA_SESSION
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.chatify.ui.newReleases.NewReleases
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var listView : ListView
    private lateinit var releasesListView: ListView

    private var mediaPlayer: MediaPlayer? = null                            // MediaPlayer Object Initialization

    var defaultUser = User()
    var defaultSession = Session(defaultUser, defaultUser.premium_priv, false)

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
                R.id.songChatFragment, R.id.albumChatFragment, R.id.artistChatFragment, R.id.genreChatFragment, R.id.myFavouriteArtists, R.id.newReleases, R.id.GetPremium, R.id.appSettingsActivity, R.id.nav_home), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (intent.extras?.get("user") != null && intent.extras?.get("session") != null)
        {
            defaultUser = intent.extras?.get("user") as User
            defaultSession = intent.extras?.get("session") as Session
        }

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
        intent.putExtra("session", defaultSession)                      // Pass Objects to Activity
        intent.putExtra("user", defaultUser)

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

    fun hideKeyboardFrom(                                                           // Hide Soft-Keyboard in Fragment
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
            val chatMessage: ChatMessage = ChatMessage("out", ContextCompat.getDrawable(this, R.drawable.in_avatar))                            // Create ChatMessage Object
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

        listView.post(Runnable {                                                    // Scroll Down to New Message
            listView.smoothScrollToPosition(adapter.count)
        })
    }

    private fun itemListener()
    {
        // ChatMessage-Item LongClick-Listener Section
        val lv: ListView = findViewById(R.id.messages_view)
        lv.onItemLongClickListener =
            OnItemLongClickListener { arg0, arg1, pos, id ->

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

        val intent = Intent(this, ReportPage::class.java)
        startActivity(intent)

        return true
    }

    private fun messageDemonstrator(chat: Chat)                                        // Randomly Create Incoming-Messages
    {
        val rand: Int = (0..99).random()

        if (rand < 20)
        {
            val ch: ChatMessage = ChatMessage("in", ContextCompat.getDrawable(this, R.mipmap.demo_avatar_0))

            ch.setTxt("I don't like the new album.")
            ch.user_name = "Holy66"

            chat.messageArray.add(ch)
        }
        else if (rand == 20 && rand < 40)
        {
            val ch: ChatMessage = ChatMessage("in", ContextCompat.getDrawable(this, R.mipmap.demo_avatar_2))

            ch.setTxt("Listening to this makes gives me Goosebumps!")
            ch.user_name = "ThePclank"

            chat.messageArray.add(ch)
        }
        else if (rand == 40 && rand < 60)
        {
            val ch: ChatMessage = ChatMessage("in", ContextCompat.getDrawable(this, R.mipmap.demo_avatar_1))

            ch.setTxt("Check out What The Dead Men Say!")
            ch.user_name = "NikNuct"

            chat.messageArray.add(ch)
        }
        else if (rand == 60 && rand < 80)
        {
            val ch: ChatMessage = ChatMessage("in", ContextCompat.getDrawable(this, R.mipmap.demo_avatar_3))

            ch.setTxt("I love Djent!!!")
            ch.user_name = "LordDJENT"

            chat.messageArray.add(ch)
        }
        else
        {
            val ch: ChatMessage = ChatMessage("in", ContextCompat.getDrawable(this, R.mipmap.demo_avatar_4))

            ch.setTxt("Nice!")
            ch.user_name = "meinshred43"

            chat.messageArray.add(ch)
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.cow_bell)
        mediaPlayer?.setOnPreparedListener {}                           // Optional For Debugging Purposes
        mediaPlayer?.start()

        publishMessage(chat.messageArray, chat)
    }

    private fun defineArtists(spotify_user: String)
    {
        val artist_list: Array<String>? = getMyArtists(spotify_user)

        if (artist_list != null)
        {
            // TODO Populate Radio Buttons on MyFavouriteArtists Page
        }
    }

    private fun getMyArtists(spotify_user: String): Array<String>?
    {
        var artist_list: Array<String>? = null
        //TODO Some Query To Receive Artists From Spotify

        return artist_list
    }

    fun getNewReleases(): String                                        // Called On Switch to NewReleases Fragment
    {
        var spotify_data: String = ""

        if (defaultSession.isSpotifyLinked())
        {
            // TODO Spotify Query for New Releases
            spotify_data = "PLACEHOLDER"

            Handler().postDelayed({                                                 // TODO Could Omit
                findViewById<ProgressBar>(R.id.releasesProgressBar).visibility = View.INVISIBLE
                findViewById<ListView>(R.id.releasesListView).visibility = View.VISIBLE
            }, 4000)
        }
        else                                                    // Spotify Isn't Connected
        {
            spotify_data = "FALSE"

            Handler().postDelayed({
                displaySpotifyPage()
            }, 4000)
        }

        return spotify_data
    }

    private fun displaySpotifyPage()
    {
        val intent = Intent(this, SpotifyConnect::class.java)
        intent.putExtra("session", defaultSession)                      // Pass Objects to Activity
        intent.putExtra("user", defaultUser)

        startActivity(intent)
    }

    fun releasesHelper()                                        // Helper Function For displayNewReleases Function
    {
        // TODO (Demo Purposes Only!)
        val r1 = Release("The Division Bell", "Pink Floyd", "28 March 1994")
        val r2 = Release("In Waves", "Trivium", "2 August 2011")
        val r3 = Release("Morrison Hotel", "The Doors", "9 February 1970")
        val r4 = Release("A Whiter Shade of Pale", "Procol Harum", "12 May 1967")

        var releases: ArrayList<Release> = ArrayList()
        releases.add(r1)
        releases.add(r2)
        releases.add(r3)
        releases.add(r4)

        releasesListView = findViewById(R.id.releasesListView)
        val adapter = ReleasesAdapter(this, releases)

        releasesListView.adapter = adapter
    }

}
