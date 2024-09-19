package com.example.waterreminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import android.os.Build

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            showNotification(context)
        }
    }

    private fun showNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification Channel (required for Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "water_reminder_channel", "Water Reminder", NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for water reminders"
            }
            notificationManager.createNotificationChannel(channel)
        }

        // Intent to open your app when the notification is tapped
        val notificationIntent = Intent(context, ClockActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, "water_reminder_channel")
            .setContentTitle("Drink Water Reminder")
            .setContentText("Time to drink water!")
            .setSmallIcon(R.drawable.app_icon) // Make sure the icon exists
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1001, notification)
    }
}
