package com.example.apotek

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class RegisterDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ApotekDB.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_USER = "users"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_FULL_NAME = "fullName"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTable = "CREATE TABLE $TABLE_USER (" +
                "$COLUMN_USERNAME TEXT PRIMARY KEY, " +
                "$COLUMN_FULL_NAME TEXT, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_PASSWORD TEXT)"
        db?.execSQL(createUserTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    // Fungsi untuk menambah pengguna baru
    fun addUser(fullName: String, email: String, username: String, password: String) {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_FULL_NAME, fullName)
            put(COLUMN_EMAIL, email)
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        db.insert(TABLE_USER, null, contentValues)
    }

    // Fungsi untuk memeriksa apakah username sudah ada
    fun checkUsername(username: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.query(TABLE_USER, arrayOf(COLUMN_USERNAME), "$COLUMN_USERNAME = ?", arrayOf(username), null, null, null)
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun checkPassword(password: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.query(TABLE_USER, arrayOf(COLUMN_PASSWORD), "$COLUMN_PASSWORD = ?", arrayOf(password), null, null, null)
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

// Fungsi untuk memeriksa apakah username dan password sesuai
    fun checkUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_USER,
            arrayOf(COLUMN_USERNAME),
            "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(username, password),
            null,
            null,
            null
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    // Fungsi untuk mendapatkan data pengguna berdasarkan username
    fun getUserData(username: String): User {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_USER,
            null,
            "$COLUMN_USERNAME = ?",
            arrayOf(username),
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) {
            val fullName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULL_NAME))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
            cursor.close()
            User(username, fullName, email, "") // Password tidak perlu ditampilkan
        } else {
            cursor.close()
            throw IllegalArgumentException("User not found")
        }
    }


    // Fungsi untuk mendapatkan password pengguna
    fun getPassword(username: String): String? {
        val db = readableDatabase
        val cursor = db.query(TABLE_USER, arrayOf(COLUMN_PASSWORD), "$COLUMN_USERNAME = ?", arrayOf(username), null, null, null)
        return if (cursor.moveToFirst()) {
            val password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            cursor.close()
            password
        } else {
            cursor.close()
            null
        }
    }

    // Fungsi untuk memperbarui data pengguna
    fun updateUser(username: String, fullName: String, email: String, password: String): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_FULL_NAME, fullName)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }

        return db.update(TABLE_USER, contentValues, "$COLUMN_USERNAME = ?", arrayOf(username)) > 0
    }
}
