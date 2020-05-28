package com.example.chatify.ui.newReleases

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.chatify.Calendar
import com.example.chatify.MainActivity
import com.example.chatify.R
import com.example.chatify.Session
import com.example.chatify.SpotifyConnect
import com.google.android.material.snackbar.Snackbar
import java.util.*


class NewReleases : Fragment() {

    companion object {
        fun newInstance() = NewReleases()
    }

    private lateinit var viewModel: NewReleasesViewModel
    
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = "New Releases"                     //Set Title

        return inflater.inflate(R.layout.new_releases_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewReleasesViewModel::class.java)

        val spotify_data: String = (activity as MainActivity).getNewReleases()

        if (spotify_data == "FALSE")
        {
            view?.let { Snackbar.make(
                it,
                "You Are Not Connected",
                Snackbar.LENGTH_SHORT
            ).show()

                Handler().postDelayed({
                    Snackbar.make(
                        it,
                        "Redirecting To Login Page",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }, 1500)
            }
        }
        else
        {
            displayNewReleases(spotify_data)
        }

    }

    private fun displayNewReleases(spotify_data: String)
    {
        // TODO Add Functionality
        Handler().postDelayed({
            displayNotifPopUp()
        }, 10000)
    }

    private fun displayNotifPopUp()
    {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setPositiveButton("Yes"){ dialogInterface, which ->
            createCalendar()
            }

        builder?.setNegativeButton("No", null)

        builder?.setTitle("Add to Calendar")
        builder?.setMessage("Add New Releases to your Google Calendar?")

        builder?.show()
    }

    private fun createCalendar()
    {
        // TODO Add Functionality
        val c1 = Calendar("", "", "")
    }

}
