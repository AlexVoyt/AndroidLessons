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
    private fun createNotificationChannel(channelID: String, channelName: String, channelDescription: String)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = channelDescription
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.Notification1);

        var notificationsUnnoticed: Int = 0;

        createNotificationChannel("Channel ID", "Channel Name", "Channel Description")
        button1.setOnClickListener {
            notificationsUnnoticed++;
            var notificationID = notificationsUnnoticed;

            val intent = Intent(this, DismissReceiver::class.java)
            intent.putExtra("com.example.notification.notificationId", notificationID)
            val pendingIntent = PendingIntent.getBroadcast(this.applicationContext, notificationID, intent, 0)

            var builder = NotificationCompat.Builder(this, "Channel ID")
                .setDeleteIntent(pendingIntent)

            if (notificationsUnnoticed >= 3)
            {
                // We set notificationID to constant value in order to not create new
                // notifications and to update existing one
                notificationID = 3
                // Delete our previous notifications
                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancelAll()

                builder.setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("You missed $notificationsUnnoticed notifications")
                    .setContentText("notice me senpai")
                    .setAutoCancel(true).priority = NotificationCompat.PRIORITY_DEFAULT
            }
            else
            {
                builder.setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Very important")
                    .setContentText("Trust me")
                    .setAutoCancel(true).priority = NotificationCompat.PRIORITY_DEFAULT
            }

            with(NotificationManagerCompat.from(this)) {
                notify(notificationID, builder.build()) // посылаем уведомление
            }

            // NOTE: how
            
        }
    }
}