package com.example.apotek

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var fullNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var registerDB: RegisterDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_edit)

        // Inisialisasi database
        registerDB = RegisterDB(this)

        // Inisialisasi EditText
        fullNameEditText = findViewById(R.id.fullNameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        // Ambil data yang dikirim dari ProfileActivity
        val fullName = intent.getStringExtra("FULL_NAME") ?: ""
        val email = intent.getStringExtra("EMAIL") ?: ""
        val username = intent.getStringExtra("USERNAME") ?: ""

        // Ambil kata sandi dari database
        val password = registerDB.getPassword(username)

        // Set data ke EditText
        fullNameEditText.setText(fullName)
        emailEditText.setText(email)
        usernameEditText.setText(username)
        passwordEditText.setText(password)

        // Tombol simpan
        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            // Ambil nilai baru dari EditText
            val newFullName = fullNameEditText.text.toString()
            val newEmail = emailEditText.text.toString()
            val newUsername = usernameEditText.text.toString()
            val newPassword = passwordEditText.text.toString()

            // Simpan perubahan ke database
            registerDB.updateUser(username, newFullName, newEmail, newPassword)

            // Kembali ke ProfileActivity dengan data yang sudah diperbarui
            val intent = Intent()
            intent.putExtra("FULL_NAME", newFullName)
            intent.putExtra("EMAIL", newEmail)
            intent.putExtra("USERNAME", newUsername)
            setResult(RESULT_OK, intent)
            finish()
        }

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            // Kembali ke ProfileActivity tanpa menyimpan
            finish()
        }
    }
}
