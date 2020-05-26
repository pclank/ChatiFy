package com.example.chatify

import java.util.*

class SupportTicket (val user_id: Int)
{
    var mod_id: Int? = null
    val ticket_id: Int = (0..500).random()      // TODO Add Proper Initialization
    val timestamp: String = ""                  // TODO Add Timestamp
    var complete: Boolean = false
    var rating: Int? = null

    fun setComplete()
    {
        this.complete = true
    }

    fun setRating(rating: Int): Boolean
    {
        var result: Boolean = false
        if (rating == 0 && rating <= 10)            // Rating Limitation Check
        {
            this.rating = rating

            result = true
        }

        return result
    }

}