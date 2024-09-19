package com.example.waterreminder

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

class BedActivity : AppCompatActivity() {

    private lateinit var bedTimeTextView: TextView
    private lateinit var selectBedTimeButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bed) // Ensure this matches your actual layout file name

        // Initialize views
        bedTimeTextView = findViewById(R.id.bed_time_text)
        selectBedTimeButton = findViewById(R.id.select_bed_time_button)
        nextButton = findViewById(R.id.buttonbed)

        // Set click listener for selecting bed time
        selectBedTimeButton.setOnClickListener {
            showTimePickerDialog()
        }

        // Set click listener for navigating to BedActivity
        nextButton.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
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
            bedTimeTextView.text = "Bed time: $selectedTime"
            Toast.makeText(this, "Bed time set to $selectedTime", Toast.LENGTH_SHORT).show()
        }, currentHour, currentMinute, true) // Use 24-hour format

        timePickerDialog.show()
    }
}
