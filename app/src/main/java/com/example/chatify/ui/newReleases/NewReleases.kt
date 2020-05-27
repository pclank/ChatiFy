package com.example.chatify.ui.newReleases

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.chatify.MainActivity
import com.example.chatify.R
import com.example.chatify.Session


class NewReleases : Fragment() {

    companion object {
        fun newInstance() = NewReleases()
    }

    private lateinit var viewModel: NewReleasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = "New Releases"                     //Set Title

        val result: Boolean = (activity as MainActivity).getNewReleases()
        val defaultSession: Session = (activity as MainActivity).defaultSession

        return inflater.inflate(R.layout.new_releases_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewReleasesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun displayNewReleases()
    {
        // TODO Add Functionality
    }



}
