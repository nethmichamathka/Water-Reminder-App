package com.example.waterreminder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PageThreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagethree)

        // References to ImageViews and TextViews
        val imageView1: ImageView = findViewById(R.id.imageView1)
        val textView1: TextView = findViewById(R.id.textView)

        val imageView2: ImageView = findViewById(R.id.imageView)
        val textView2: TextView = findViewById(R.id.textView3)

        val imageView3: ImageView = findViewById(R.id.imageView3)
        val textView3: TextView = findViewById(R.id.textView5)

        // Next button
        val nextButton: Button = findViewById(R.id.button3)

        nextButton.setOnClickListener {
            // Intent to navigate to another activity (for example, PageTwoActivity)
            val intent = Intent(this, PermissionActivity::class.java) // Replace with your target activity
            startActivity(intent)
        }
    }
}
