package com.example.apotek

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrasi)

        dbHelper = DatabaseHelper(this)

        val registerButton: Button = findViewById(R.id.registerButton)
        val masukText: TextView = findViewById(R.id.masukText)
        val nameEditText: EditText = findViewById(R.id.fullName)
        val emailEditText: EditText = findViewById(R.id.email)
        val usernameEditText: EditText = findViewById(R.id.username)
        val passwordEditText: EditText = findViewById(R.id.password)
        val confirmPasswordEditText: EditText = findViewById(R.id.confirmPassword)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            // Validasi input
            if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Silakan lengkapi semua kolom", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dbHelper.checkUsername(username)) {
                Toast.makeText(this, "Username sudah terpakai", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan pengguna ke database
            dbHelper.addUser(name, email, username, password)
            Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        masukText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
