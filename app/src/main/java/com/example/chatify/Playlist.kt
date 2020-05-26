package com.example.chatify

import android.os.Build

class Playlist (title: String, private val spotify_user: String)
{
    var title = title
    var p_list: Array<Song>? = null
    val timestamp: String = ""              // TODO Add Timestamp

}