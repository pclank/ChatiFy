package com.example.chatify.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.chatify.R
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)

        val hour: Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)                  // Get 24-Hour Clock Hour
        var text: String = ""

        // TODO Add username in message

        when (hour)
        {
            in 6..12-> text = "Good Morning, Random Citizen!"

            in 13..16-> text = "Good Afternoon, Random Citizen!"

            in 17..20-> text = "Good Evening, Random Citizen!"

            in (21..23)-> text = "Late Chatting, Random Citizen?"
            in (0..5)-> text = "Late Chatting, Random Citizen?"
        }

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = text
        })

        return root
    }
}
