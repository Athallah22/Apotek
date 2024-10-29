package com.example.apotek

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var registerDB: RegisterDB
    private lateinit var fullNameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var currentUsername: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Inisialisasi database
        registerDB = RegisterDB(this)

        // Ambil username dari Intent
        currentUsername = intent.getStringExtra("USER_NAME") ?: throw IllegalArgumentException("Username tidak boleh null")

        // Inisialisasi TextView
        fullNameTextView = findViewById(R.id.fullNameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        usernameTextView = findViewById(R.id.usernameTextView)

        // Load data pengguna
        loadUserData()

        // Inisialisasi tombol edit
        val editButton: Button = findViewById(R.id.editButton)
        editButton.setOnClickListener {
            // Kirimkan data pengguna satu per satu ke EditProfileActivity
            val editIntent = Intent(this, EditProfileActivity::class.java).apply {
                putExtra("FULL_NAME", fullNameTextView.text)
                putExtra("EMAIL", emailTextView.text)
                putExtra("USERNAME", currentUsername)
            }
            startActivityForResult(editIntent, EDIT_PROFILE_REQUEST_CODE)
        }

        val backButton : ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            // Arahkan ke KeranjangActivity
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }

    private fun loadUserData() {
        // Ambil data pengguna dari database menggunakan currentUsername
        val userData = registerDB.getUserData(currentUsername)
        fullNameTextView.text = "Nama Lengkap: ${userData.fullName}"
        emailTextView.text = "Email: ${userData.email}"
        usernameTextView.text = "Nama Pengguna: ${userData.username}"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Setelah mengedit, muat ulang data pengguna
            loadUserData()
        }
    }

    companion object {
        private const val EDIT_PROFILE_REQUEST_CODE = 1
    }
}
