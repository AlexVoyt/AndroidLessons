package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.app.PendingIntent
import android.util.Log


class MainActivity : AppCompatActivity() {
    private fun createNotificationChannel(
        channelID: String,
        channelName: String,
        channelDescription: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = channelDescription
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun readFromSharedPref(str: String): Int
    {
        return this.getSharedPreferences("SP", Context.MODE_PRIVATE).getInt(str, 0)
    }

    private fun writeToSharedPref(str: String, value: Int)
    {
        val sharedPref = this.getSharedPreferences("SP", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt((str), value)
            apply()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button1: Button = findViewById(R.id.Notification1);
        var notificationsUnnoticed1: Int = 0;
        writeToSharedPref("notificationUnnoticed1", 0)
        writeToSharedPref("multipleUnnoticed1", 0)
        createNotificationChannel("Channel ID1", "Channel Name1", "Channel Description1")

        button1.setOnClickListener {
            // Telling DismissReceiver to work with first channel
            writeToSharedPref("useChannel1", 1)
            writeToSharedPref("useChannel2", 0)

            // incrementing unnoticed counter
            writeToSharedPref("notificationUnnoticed1", readFromSharedPref("notificationUnnoticed1") + 1)
            var notificationID = readFromSharedPref("notificationUnnoticed1")
            notificationsUnnoticed1 = notificationID

            val intent = Intent(this, DismissReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this.applicationContext, 0, intent, 0)

            var builder = NotificationCompat.Builder(this, "Channel ID1")
                .setDeleteIntent(pendingIntent)

            if (notificationsUnnoticed1 >= 3)
            {
                // We set notificationID to constant value in order to not create new
                // notifications and to update existing one
                notificationID = 3

                // Tell DismissReceiver not to decrement out unnoticed count, but set to 0
                writeToSharedPref("multipleUnnoticed1", 1)

                // Delete our previous notifications
                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancelAll()

                builder.setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("CH1: You missed $notificationsUnnoticed1 notifications")
                    .setContentText("notice me senpai")
                    .setAutoCancel(true).priority = NotificationCompat.PRIORITY_DEFAULT
            }
            else
            {
                builder.setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("CH1: Very important")
                    .setContentText("Trust me")
                    .setAutoCancel(true).priority = NotificationCompat.PRIORITY_DEFAULT
            }

            with(NotificationManagerCompat.from(this)) {
                notify(notificationID, builder.build()) // посылаем уведомление
            }

            Log.d("Click", "Unnoticed notifications from CH1: $notificationsUnnoticed1")
        }

        // TODO: I know, I know, copy-paste is bad, but modern times requires modern solutions
        val button2: Button = findViewById(R.id.Notification2);
        var notificationsUnnoticed2: Int = 0;
        writeToSharedPref("notificationUnnoticed2", 0)
        writeToSharedPref("multipleUnnoticed2", 0)
        createNotificationChannel("Channel ID2", "Channel Name2", "Channel Description2")

        button2.setOnClickListener {
            // Telling DismissReceiver to work with second channel
            writeToSharedPref("useChannel1", 0)
            writeToSharedPref("useChannel2", 1)

            // incrementing unnoticed counter
            writeToSharedPref("notificationUnnoticed2", readFromSharedPref("notificationUnnoticed2") + 1)
            var notificationID = readFromSharedPref("notificationUnnoticed2")
            notificationsUnnoticed2 = notificationID

            val intent = Intent(this, DismissReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this.applicationContext, 0, intent, 0)

            var builder = NotificationCompat.Builder(this, "Channel ID2")
                .setDeleteIntent(pendingIntent)

            if (notificationsUnnoticed1 >= 3)
            {
                // We set notificationID to constant value in order to not create new
                // notifications and to update existing one
                notificationID = 3

                // Tell DismissReceiver not to decrement out unnoticed count, but set to 0
                writeToSharedPref("multipleUnnoticed2", 1)

                // Delete our previous notifications
                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancelAll()

                builder.setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("CH2: You missed $notificationsUnnoticed2 notifications")
                    .setContentText("notice me senpai")
                    .setAutoCancel(true).priority = NotificationCompat.PRIORITY_DEFAULT
            }
            else
            {
                builder.setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("CH2: Very important")
                    .setContentText("Trust me")
                    .setAutoCancel(true).priority = NotificationCompat.PRIORITY_DEFAULT
            }

            with(NotificationManagerCompat.from(this)) {
                notify(notificationID, builder.build()) // посылаем уведомление
            }

            Log.d("Click", "Unnoticed notifications from CH2: $notificationsUnnoticed2")
        }
    }
}