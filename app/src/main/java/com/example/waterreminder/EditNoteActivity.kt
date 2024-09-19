// EditNoteActivity.kt
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

        editNoteEditText = findViewById(R.id.edit_note)
        saveNoteButton = findViewById(R.id.save_note_button)

        // Get the note details from the intent
        val noteText = intent.getStringExtra("NOTE_TEXT")
        noteId = intent.getIntExtra("NOTE_ID", -1)

        editNoteEditText.setText(noteText)

        saveNoteButton.setOnClickListener {
            val updatedNoteText = editNoteEditText.text.toString()
            val resultIntent = Intent().apply {
                putExtra("NOTE_TEXT", updatedNoteText)
                putExtra("NOTE_ID", noteId)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
