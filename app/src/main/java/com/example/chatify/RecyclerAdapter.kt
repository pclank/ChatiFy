package com.example.chatify

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_row.view.*


class RecyclerAdapter(private val context: ReportPage, private val reasonsList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false))
    }

    override fun getItemCount(): Int {
        return reasonsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.reasonName?.text = reasonsList[position]

        holder.itemView.setBackgroundColor(Color.parseColor("#5bb7ed"))
        if (selectedPosition == position)
            holder.itemView.setBackgroundColor(Color.parseColor("#5bb7ed"))
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#fafafa"))

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reasonName = view.reportReason
    }
}