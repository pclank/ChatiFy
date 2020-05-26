package com.example.chatify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_row.view.*


class RecyclerAdapter(private val context: ReportPage, private val reasonsList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false))
    }

    override fun getItemCount(): Int {
        return reasonsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.reasonName?.text = reasonsList[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(context, reasonsList[position], Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reasonName = view.reportReason
    }
}