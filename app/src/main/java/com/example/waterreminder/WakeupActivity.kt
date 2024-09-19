package com.example.waterreminder

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class WakeupActivity : AppCompatActivity() {

    private lateinit var wakeupTimeTextView: TextView
    private lateinit var selectWakeupTimeButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wakeup) // Ensure this matches your actual layout file name

        // Initialize views
        wakeupTimeTextView = findViewById(R.id.wakeup_time_text)
        selectWakeupTimeButton = findViewById(R.id.select_wakeup_time_button)
        nextButton = findViewById(R.id.buttonwakeup)

        // Set click listener for selecting wakeup time
        selectWakeupTimeButton.setOnClickListener {
            showTimePickerDialog()
        }

        // Set click listener for navigating to BedActivity
        nextButton.setOnClickListener {
            val intent = Intent(this, BedActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showTimePickerDialog() {
        // Get the current time for default value
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        // Create and show TimePickerDialog
        val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute ->
            // Format and display selected time
            val selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
            wakeupTimeTextView.text = "Wakeup time: $selectedTime"
            Toast.makeText(this, "Wakeup time set to $selectedTime", Toast.LENGTH_SHORT).show()
        }, currentHour, currentMinute, true) // Use 24-hour format

        timePickerDialog.show()
    }
}
