package com.example.chatify

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class ReleasesAdapter(private val context: Context, private val releaseArray: ArrayList<Release>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        val release = getItem(position) as Release


        val rowView = inflater.inflate(R.layout.release, parent, false)
        val titleView = rowView.findViewById(R.id.release_title) as TextView
        val artistView = rowView.findViewById(R.id.release_artist) as TextView
        val dateView = rowView.findViewById(R.id.release_date) as TextView

        titleView.text = release.title
        artistView.text = release.artist
        dateView.text = release.date

        return rowView

    }

    override fun getItem(position: Int): Any
    {
        return releaseArray[position]
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun getCount(): Int
    {
        // TODO Could be omitted
        return releaseArray.size
    }
}