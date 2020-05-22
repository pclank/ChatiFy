package com.example.chatify

open class Chat
{
    var type: String = ""
    var timestamp: String = ""
    var population: Int? = null
    var mod_username: String = ""
    var chat_id: Int? = null

    var message_array: ArrayList<ChatMessage> = ArrayList()         // For Presentation Purposes Only
}