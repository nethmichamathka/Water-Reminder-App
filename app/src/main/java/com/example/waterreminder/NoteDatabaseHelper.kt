package com.example.waterreminder.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "notes.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NOTES = "notes"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_TEXT = "text"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NOTES ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_TEXT TEXT)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        onCreate(db)
    }

    @SuppressLint("Range")
    fun getNoteById(id: Int): String? {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NOTES, arrayOf(COLUMN_TEXT), "$COLUMN_ID=?", arrayOf(id.toString()), null, null, null)
        var noteText: String? = null
        if (cursor.moveToFirst()) {
            noteText = cursor.getString(cursor.getColumnIndex(COLUMN_TEXT))
        }
        cursor.close()
        return noteText
    }

    fun deleteNoteById(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NOTES, "$COLUMN_ID=?", arrayOf(id.toString()))
    }
}
