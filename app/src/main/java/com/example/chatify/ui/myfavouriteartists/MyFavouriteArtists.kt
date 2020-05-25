package com.example.chatify.ui.myfavouriteartists

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.chatify.Playlist
import com.example.chatify.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.get_premium.view.*
import kotlinx.android.synthetic.main.my_favourite_artists_fragment.view.*


class MyFavouriteArtists : Fragment() {

    companion object {
        fun newInstance() =
            MyFavouriteArtists()
    }

    private lateinit var viewModel: MyFavouriteArtistsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.my_favourite_artists_fragment, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = "My Favorite Artists"                     //Set Title

        view.save_changes_button.setOnClickListener { view ->
            saveFavArtists()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyFavouriteArtistsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    var spotify_user: String = ""                       // TODO Setter-Getter to Get Info From Object User

    private fun saveFavArtists()
    {
        // TODO Save for User

        val related_info: String = getRelatedInfo(spotify_user)

        view?.let {
            Snackbar.make(
                it,
                "Changes Saved",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        Handler().postDelayed({
            createPlaylist(related_info)
        }, 2000)

    }

    private fun getRelatedInfo(spotify_user: String): String                        // Most Likely JSON File
    {
        // TODO Query To Spotify For Relevant Info
        val related_info: String = "PLACEHOLDER"

        return related_info
    }

    private fun createPlaylist(related_info: String)
    {
        val pl1: Playlist = Playlist("MyPlaylist", spotify_user)

        displayPlaylistPopUp(related_info, pl1)
    }

    private fun displayPlaylistPopUp(related_info: String, pl1: Playlist)
    {
        val builder = context?.let { AlertDialog.Builder(it) }
        with(builder)
        {
            this?.setTitle("Playlist Pop-Up")
            this?.setMessage("Playlist Created Successfully! Play Now?")

            builder?.setPositiveButton("Yes"){ dialogInterface, which ->
                playPlaylist(pl1)

                view?.let {
                    Snackbar.make(
                        it,
                        "Starting Playlist",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }

            builder?.setNegativeButton("Save for later"){ dialogInterface, which ->
                titlePopUp(view, pl1)


            }

            this?.show()
        }

    }

    private fun snackHelper(result: Boolean, title: String)                    // Displays Snackbar Depending on titlePopUp()
    {
        if (result)
        {
            view?.let {
                Snackbar.make(
                    it,
                    "Playlist $title Saved on Spotify",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        else
        {
            view?.let {
                Snackbar.make(
                    it,
                    "Operation Canceled",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun playPlaylist(pl1: Playlist)
    {
        // TODO Play Songs on Spotify
    }

    private fun titlePopUp(view: View?, pl1: Playlist)                                    // Pop-Up
    {
        val builder = context?.let { AlertDialog.Builder(it) }
        val inflater = layoutInflater
        builder?.setTitle("Playlist Import")
        val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_edittext, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.titleText)

        builder?.setView(dialogLayout)
        builder?.setPositiveButton("Save") { dialogInterface, i ->
            pl1.title = editText.text.toString()
            importPlaylist(pl1, pl1.title)

            snackHelper(true, pl1.title)
        }

        builder?.setNegativeButton("Cancel") { dialogInterface, i ->
            snackHelper(false, "")
        }

        builder?.show()

    }

    private fun importPlaylist(pl1: Playlist, title: String)            // TODO Second Parameter Can Be Omitted
    {
        // TODO Query to Spotify to Save Playlist
    }

}
