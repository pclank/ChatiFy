package com.example.chatify

import java.time.format.DateTimeFormatter

open class Chat (type: String, chat_id: Int?, timestamp: String)
{
    var type: String = type
    var timestamp: String = timestamp
    var population: Int? = null
    var mod_username: String = ""
    var chat_id: Int? = chat_id

    var messageArray: ArrayList<ChatMessage> = ArrayList()         // For Presentation Purposes Only
}