package com.example.chatify.ui.songChat

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.chatify.R


class SongChatFragment : Fragment() {

    companion object {
        fun newInstance() = SongChatFragment()
    }

    private lateinit var viewModel: SongChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Song Chat"                     //Set Title

        return inflater.inflate(R.layout.song_chat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SongChatViewModel::class.java)

        // TODO: Use the ViewModel
    }

}
