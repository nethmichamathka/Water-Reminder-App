package com.example.waterreminder.data

class NoteRepository(private val dbHelper: NoteDatabaseHelper) {

    fun getNoteById(id: Int): String? {
        return dbHelper.getNoteById(id)
    }

    fun deleteById(id: Int): Int {
        return dbHelper.deleteNoteById(id)
    }
}
