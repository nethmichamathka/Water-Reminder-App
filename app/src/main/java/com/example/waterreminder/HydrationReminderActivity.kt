package com.example.waterreminder

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HydrationReminderActivity : AppCompatActivity() {

    private lateinit var notificationHelper: NotificationHelper
    private val handler = Handler(Looper.getMainLooper())
    private val reminderInterval: Long = 15 * 60 * 1000 // 15 minutes in milliseconds

    private val reminderRunnable = object : Runnable {
        override fun run() {
            notificationHelper.sendNotification()
            handler.postDelayed(this, reminderInterval)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hydration_reminder)

        notificationHelper = NotificationHelper(this)

        // Start the periodic reminder
        handler.post(reminderRunnable)

        // Handle stop reminder button click
        findViewById<Button>(R.id.stop_reminder_button).setOnClickListener {
            stopReminder()
        }
    }

    private fun stopReminder() {
        handler.removeCallbacks(reminderRunnable)
        // Show a simple Toast message when the switch is toggled
        Toast.makeText(this@HydrationReminderActivity, "stopped!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(reminderRunnable) // Prevent memory leaks
    }
}
