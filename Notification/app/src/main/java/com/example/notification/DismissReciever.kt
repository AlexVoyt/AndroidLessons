package com.example.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DismissReceiver() : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("In method onReceive", "Dismissed event")
        val notificationId = intent.extras!!.getInt("com.example.notification.notificationId")
        Log.d("ID", "$notificationId")

        // TODO: how can we reset notificationsUnnoticed??
        intent.extras!!.getInt("com.example.notification.notificationId")
    }

}
