package com.example.waterreminder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var waterImageView: ImageView
    private lateinit var reminderTextView: TextView
    private lateinit var clockButton: Button
    private lateinit var todoButton: Button
    private lateinit var hydrationReminderButton: Button  // Button for hydration reminder
    private var progressAmount = 0
    private val maxWaterAmount = 2000 // Example maximum goal in ml

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        progressBar = findViewById(R.id.progressBar)
        waterImageView = findViewById(R.id.imageView2)
        reminderTextView = findViewById(R.id.reminder_text)
        clockButton = findViewById(R.id.button_clock) // Initialize the clock button
        todoButton = findViewById(R.id.todo) // Initialize the todo button
        hydrationReminderButton = findViewById(R.id.button_hydration_reminder) // Initialize the hydration reminder button

        progressBar.max = maxWaterAmount
        updateReminderText()

        waterImageView.setOnClickListener {
            increaseProgress(200)
        }

        // Set the click listener for the "TODO" button
        todoButton.setOnClickListener {
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        }

        // Set the click listener for the "Clock" button
        clockButton.setOnClickListener {
            val intent = Intent(this, ClockActivity::class.java)
            startActivity(intent)
        }

        // Set the click listener for the "Hydration Reminder" button
        hydrationReminderButton.setOnClickListener {
            val intent = Intent(this, HydrationReminderActivity::class.java)
            startActivity(intent)
        }
    }

    private fun increaseProgress(amount: Int) {
        progressAmount += amount

        if (progressAmount > maxWaterAmount) {
            progressAmount = maxWaterAmount
        }

        progressBar.progress = progressAmount
        updateReminderText()
    }

    private fun updateReminderText() {
        val remainingAmount = maxWaterAmount - progressAmount
        reminderTextView.text = if (remainingAmount > 0) {
            "You need to drink another ${remainingAmount}ml to reach the target"
        } else {
            "Congratulations! You've reached your water goal!"
        }
    }
}
