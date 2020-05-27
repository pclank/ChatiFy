package com.example.chatify.ui.newReleases

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chatify.R


class NewReleases : Fragment() {

    companion object {
        fun newInstance() = NewReleases()
    }

    private lateinit var viewModel: NewReleasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_releases_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewReleasesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
