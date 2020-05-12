package com.example.chatify.ui.artistChat

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.chatify.R


class ArtistChatFragment : Fragment() {

    companion object {
        fun newInstance() =
            ArtistChatFragment()
    }

    private lateinit var viewModel: ArtistChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Artist Chat"                     //Set Title


        return inflater.inflate(R.layout.artist_chat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArtistChatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
