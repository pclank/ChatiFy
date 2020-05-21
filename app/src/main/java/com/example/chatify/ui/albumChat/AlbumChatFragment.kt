package com.example.chatify.ui.albumChat

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.chatify.R
import kotlinx.android.synthetic.main.album_chat_fragment.*
import kotlinx.android.synthetic.main.album_chat_fragment.view.*
import kotlinx.android.synthetic.main.album_chat_fragment.view.editText


class AlbumChatFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumChatFragment()
    }

    private lateinit var viewModel: AlbumChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Album Chat"                     //Set Title


        return inflater.inflate(R.layout.album_chat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlbumChatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
