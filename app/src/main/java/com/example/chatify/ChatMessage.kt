package com.example.chatify

import android.graphics.drawable.Drawable

class ChatMessage(val type: String, val avatar: Drawable?) : Message()
{
    var user_name: String = ""      // TODO Implement in Constructor
}