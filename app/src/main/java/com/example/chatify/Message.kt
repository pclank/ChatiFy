package com.example.chatify

open class Message
{
    var text: String = ""
    var timestamp: String = ""
    var msg_id: Int? = null

    fun setTxt(msg: String)
    {
        text = msg
    }
}