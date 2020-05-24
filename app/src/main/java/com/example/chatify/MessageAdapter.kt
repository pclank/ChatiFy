package com.example.chatify

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class MessageAdapter(private val context: Context, private val msgArray: ArrayList<ChatMessage>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        val msg = getItem(position) as ChatMessage

        if (msg.type == "in")
        {
            val rowView = inflater.inflate(R.layout.in_message, parent, false)
            val bodyView = rowView.findViewById(R.id.message_body) as TextView
            val nameView = rowView.findViewById(R.id.name) as TextView
            val avatarView = rowView.findViewById(R.id.avatar) as View

            bodyView.text = msg.text
            nameView.text = msg.user_name
            avatarView.background = msg.avatar

            return rowView
        }
        else
        {
            val rowView = inflater.inflate(R.layout.out_message, parent, false)
            val bodyView = rowView.findViewById(R.id.message_body) as TextView

            bodyView.text = msg.text

            return rowView
        }
    }

    override fun getItem(position: Int): Any
    {
        return msgArray[position]
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun getCount(): Int
    {
        // TODO Could be omitted
        return msgArray.size
    }
}