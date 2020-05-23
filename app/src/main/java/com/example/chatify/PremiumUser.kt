package com.example.chatify

class PremiumUser : User()
{
    fun renewSub(): Boolean                 // Confirm Renewal Transaction
    {
        // TODO (Add Functionality)

        return true
    }

    fun getSubStatus(): Boolean
    {
        // TODO (Read Type of Subscription)

        return true                         // Subscription Is Valid
    }
}