package com.example.chatify.ui.genreChat

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.chatify.R


class GenreChatFragment : Fragment() {

    companion object {
        fun newInstance() = GenreChatFragment()
    }

    private lateinit var viewModel: GenreChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Genre Chat"                     //Set Title

        return inflater.inflate(R.layout.genre_chat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GenreChatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
