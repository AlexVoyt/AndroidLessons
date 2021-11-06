package com.example.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DismissReceiver() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val sharedPref = context.getSharedPreferences("SP", Context.MODE_PRIVATE)
        var channel: Int
        if(sharedPref.getInt("useChannel1", 0) == 1)
        {
            channel = 1
        }
        else if(sharedPref.getInt("useChannel2", 0) == 1)
        {
            channel = 2
        }
        else
        {
            // NOTE: this should not be reachable
            Log.d("Bruh", "YOU PICKED THE WRONG HOUSE FOOL")
            channel = 3 // Kotlin says variable must be initialized, so okay
        }


        // decrementing unnoticed count
        val currentUnnoticed = sharedPref.getInt("notificationUnnoticed$channel", 0);
        val shouldSetToZero = sharedPref.getInt("multipleUnnoticed$channel", 0);

        with (sharedPref.edit()) {
            if(shouldSetToZero == 1)
            {
                putInt("notificationUnnoticed$channel", 0)
                putInt("multipleUnnoticed$channel", 0) // we read all our unnoticed events
                apply()
            }
            else
            {
                putInt("notificationUnnoticed$channel", currentUnnoticed - 1)
                apply()
            }

        }

    }

}
