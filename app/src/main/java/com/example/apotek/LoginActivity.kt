package com.example.apotek

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Inisialisasi EditText dan Button
        val usernameEditText: EditText = findViewById(R.id.Username)
        val passwordEditText: EditText = findViewById(R.id.Password)
        val loginButton: Button = findViewById(R.id.masuk) // ID button login Anda

        loginButton.setOnClickListener {
            // Ambil nilai dari EditText
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Lakukan validasi
            if (username == "1" && password == "1") {
                // Jika login berhasil
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish() // Tutup LoginActivity agar tidak bisa kembali
            } else {
                // Jika login gagal, tampilkan pesan
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
