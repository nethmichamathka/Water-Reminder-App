package com.example.waterreminder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PageOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout to the XML file created for this activity
        setContentView(R.layout.activity_pageone)

        // Reference to the "Next" button
        val nextButton = findViewById<Button>(R.id.button1)

        // Set onClickListener to the Next button
        nextButton.setOnClickListener {
            // Intent to navigate to another activity (for example, PageTwoActivity)
            val intent = Intent(this, PageTwoActivity::class.java) // Replace with your target activity
            startActivity(intent)
        }
    }
}

