package com.example.chatify

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.LocalDateTime.now

class Playlist (private var title: String, private val spotify_user: String)
{
    var p_list: Array<Song>? = null
    @RequiresApi(Build.VERSION_CODES.O)
    val timestamp: String = now().toString()

}