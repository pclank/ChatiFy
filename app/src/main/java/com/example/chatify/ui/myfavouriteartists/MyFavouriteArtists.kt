package com.example.chatify.ui.myfavouriteartists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
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

    fun saveFavArtists()
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
    }

    private fun getRelatedInfo(spotify_user: String): String                        // Most Likely JSON File
    {
        // TODO Query To Spotify For Relevant Info
        val related_info: String = "PLACEHOLDER"

        return related_info
    }

}
