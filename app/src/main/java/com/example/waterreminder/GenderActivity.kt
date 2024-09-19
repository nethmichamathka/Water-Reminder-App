package com.example.waterreminder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GenderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender) // Ensure your layout file name is correct

        // Find the switches from the layout
        val maleSwitch = findViewById<Switch>(R.id.notification_switch1)
        val femaleSwitch = findViewById<Switch>(R.id.notification_switch2)

        // Find the button from the layout
        val button = findViewById<Button>(R.id.button1)

        // Set an OnCheckedChangeListener for male switch
        maleSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this@GenderActivity, "Male selected", Toast.LENGTH_SHORT).show()
                // Optionally uncheck female switch if this is an exclusive selection
                femaleSwitch.isChecked = false
            }
        }

        // Set an OnCheckedChangeListener for female switch
        femaleSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this@GenderActivity, "Female selected", Toast.LENGTH_SHORT).show()
                // Optionally uncheck male switch if this is an exclusive selection
                maleSwitch.isChecked = false
            }
        }

        // Handle button click
        button.setOnClickListener {
            // Implement your logic for button click here
            Toast.makeText(this@GenderActivity, "Button clicked!", Toast.LENGTH_SHORT).show()

            // You can navigate to another activity if needed
            val intent = Intent(this@GenderActivity, WakeupActivity::class.java)
            startActivity(intent)
        }
    }
}
