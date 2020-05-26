package com.example.chatify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.report_interface.*

class ReportPage : AppCompatActivity() {
    val reasonsList: ArrayList<String> = ArrayList()

    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_interface)
        reasonsList.add("Spam")
        reasonsList.add("Abusive Speech")
        reasonsList.add("Threatening a user")
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RecyclerAdapter(this, reasonsList)
    }
}


