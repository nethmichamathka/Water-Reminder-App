package com.example.waterreminder

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PermissionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permision) // Ensure the correct layout file name

        // Find the switch from the layout
        val notificationSwitch = findViewById<Switch>(R.id.notification_switch)

        // Set an OnCheckedChangeListener to handle switch state changes
        notificationSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            if (isChecked) {
                // Show a simple Toast message when the switch is toggled
                Toast.makeText(this@PermissionActivity, "Switch toggled!", Toast.LENGTH_SHORT).show()

                // Navigate to GenderActivity when the switch is toggled on
                val intent = Intent(this@PermissionActivity, GenderActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
