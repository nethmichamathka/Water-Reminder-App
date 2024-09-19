package com.example.waterreminder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    // Declare variables for the ProgressBar, ImageView, and TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var waterImageView: ImageView
    private lateinit var reminderTextView: TextView
    private lateinit var clockButton: Button
    private var progressAmount = 0
    private val maxWaterAmount = 1830 // Example maximum goal in ml

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize the ProgressBar, ImageView, and TextView
        progressBar = findViewById(R.id.progressBar)
        waterImageView = findViewById(R.id.imageView2)
        reminderTextView = findViewById(R.id.reminder_text)
        clockButton = findViewById(R.id.button_clock) // Initialize the button

        // Set max value for the ProgressBar
        progressBar.max = maxWaterAmount

        // Set the initial text for remaining water
        updateReminderText()

        // Set the click listener on the ImageView (to act like a button)
        waterImageView.setOnClickListener {
            // Increment progress (example amount of 250ml)
            increaseProgress(250)
        }

        // Set the click listener on the Clock Button
        clockButton.setOnClickListener {
            // Navigate to ClockActivity
            val intent = Intent(this, ClockActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to increase the ProgressBar
    private fun increaseProgress(amount: Int) {
        progressAmount += amount

        // Ensure the progress doesn't exceed the max limit
        if (progressAmount > maxWaterAmount) {
            progressAmount = maxWaterAmount
        }

        // Update the ProgressBar
        progressBar.progress = progressAmount

        // Update the reminder text for remaining water
        updateReminderText()
    }

    // Function to update the reminder text based on the progress
    private fun updateReminderText() {
        val remainingAmount = maxWaterAmount - progressAmount
        if (remainingAmount > 0) {
            reminderTextView.text = "You need to drink another ${remainingAmount}ml to reach the target"
        } else {
            reminderTextView.text = "Congratulations! You've reached your water goal!"
        }
    }
}
