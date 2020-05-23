package com.example.chatify

class Session (private val user_id: Int, private val prem_ses: Boolean, var spot_linked: Boolean)
{
    val session_id: Int = (0..9999).random()            // TODO (Demonstration Purposes Only!)
    var ses_time: Float = 0F
    val country: String? = null

    fun disconnectUser()
    {
        // TODO (End References to Session and Log User Out)
    }

    fun isSpotifyLinked(): Boolean                      // Can Be Omitted
    {
        return spot_linked
    }
}