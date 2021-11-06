package com.example.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DismissReceiver() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // decrementing unnoticed count

        val sharedPref = context.getSharedPreferences("SP", Context.MODE_PRIVATE)
        val currentUnnoticed = sharedPref.getInt("notificationUnnoticed", 0);
        val shouldSetToZero = sharedPref.getInt("multipleUnnoticed", 0);

        with (sharedPref.edit()) {
            if(shouldSetToZero == 1)
            {
                putInt("notificationUnnoticed", 0)
                putInt("multipleUnnoticed", 0) // we read all our unnoticed events
                apply()
            }
            else
            {
                putInt("notificationUnnoticed", currentUnnoticed - 1)
                apply()
            }

        }


        // TODO: how can we reset notificationsUnnoticed??

    }

}
