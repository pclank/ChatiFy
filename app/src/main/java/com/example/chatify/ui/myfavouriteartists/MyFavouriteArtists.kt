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

        (activity as AppCompatActivity).supportActionBar?.title = "My Favorite Artists"                     //Set Title

        // TODO (Revise): val button: Button = fragment.findViewById(R.id.save_changes_button)



        return inflater.inflate(R.layout.my_favourite_artists_fragment, container, false)
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
    }

    private fun getRelatedInfo(spotify_user: String): String                        // Most Likely JSON File
    {
        // TODO Query To Spotify For Relevant Info
        val related_info: String = "PLACEHOLDER"

        return related_info
    }

}
