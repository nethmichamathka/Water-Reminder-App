package com.example.waterreminder

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditNoteActivity : AppCompatActivity() {

    private lateinit var editNoteEditText: EditText
    private lateinit var saveNoteButton: Button
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        // Initialize the EditText and Button views
        editNoteEditText = findViewById(R.id.edit_note)
        saveNoteButton = findViewById(R.id.save_note_button)

        // Get the note details passed from the intent
        val noteText = intent.getStringExtra("NOTE_TEXT")
        noteId = intent.getIntExtra("NOTE_ID", -1)

        // Set the note text in the EditText if available
        editNoteEditText.setText(noteText)

        // Set a click listener for the Save button
        saveNoteButton.setOnClickListener {
            val updatedNoteText = editNoteEditText.text.toString()

            // Create an Intent to return the updated note data to MainActivity
            val resultIntent = Intent().apply {
                putExtra("NOTE_TEXT", updatedNoteText)
                putExtra("NOTE_ID", noteId)
            }

            // Set the result as RESULT_OK and attach the updated note data
            setResult(Activity.RESULT_OK, resultIntent)

            // Finish the activity and return to MainActivity
            finish()
        }
    }
}
