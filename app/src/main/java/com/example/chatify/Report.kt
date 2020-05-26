package com.example.chatify

class Report (val user_id: Int, val target_id: Int, val description: String)
{
    var mod_id: Int? = null
    val report_id: Int = (0..1000).random()     // TODO Add Proper Initialization
    val timestamp: String = ""                  // TODO Add Timestamp
    var complete: Boolean = false

    fun setComplete()
    {
        this.complete = true
    }

}