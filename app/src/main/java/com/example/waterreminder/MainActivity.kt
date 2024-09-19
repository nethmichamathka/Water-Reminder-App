package com.example.waterreminder

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Make sure this points to your XML layout file

        // Find the button from the layout
        val splashButton = findViewById<Button>(R.id.button1)

        // Set an onClickListener to handle button click events
        splashButton.setOnClickListener {
            // Option 1: Show a simple Toast message when the button is clicked
            Toast.makeText(this@MainActivity, "here we go!", Toast.LENGTH_SHORT)
                .show()

            // Option 2: Navigate to SignUpActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            // Intent intent = new Intent(MainActivity.this, NextActivity.class);
            // startActivity(intent);
        }
    }
}