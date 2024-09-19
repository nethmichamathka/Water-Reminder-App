// MainActivity.kt
package com.example.waterreminder

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class TodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        val imageButton: ImageButton = findViewById(R.id.imageButton)
        imageButton.setOnClickListener {
            // Start AddNoteActivity
            val intent = Intent(this, DialogAddNoteActivity::class.java)
            startActivity(intent)
        }
    }
}
