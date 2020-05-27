package com.example.chatify

import java.io.Serializable

open class User : Serializable
{
    var username: String? = ""
    var spotify_user: String? = null
    var premium_priv: Boolean = false
    val user_id: Int = (0..9999).random()               // TODO (Presentation Only!)
    var warnings: Int = 0
    var ban: Boolean = false

    fun enablePremiumFeatures(): Boolean
    {
        // TODO (Add Further Functionality)
        this.premium_priv = true

        return true                                     // Confirm Success
    }

    fun addSpotifyData(username: String)
    {
        this.spotify_user = username
    }

    fun userDeletion()                                  // From DeletionConfirmationScreen
    {
        // TODO (Remove From Database)
        this.username = null
        this.spotify_user = null
        this.premium_priv = false
        this.ban = true
    }

    fun addWarning()
    {
        this.warnings++
    }

    fun addBan()
    {
        this.ban = true
    }
}