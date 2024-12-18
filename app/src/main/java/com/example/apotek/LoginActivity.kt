package com.example.apotek

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var dbHelper: RegisterDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        dbHelper = RegisterDB(this)

        val usernameEditText: EditText = findViewById(R.id.Username)
        val passwordEditText: EditText = findViewById(R.id.Password)
        val loginButton: Button = findViewById(R.id.masuk)
        val registerText: TextView = findViewById(R.id.registerText)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (dbHelper.checkUser(username, password)) {
                val menuIntent = Intent(this, MenuActivity::class.java)
                menuIntent.putExtra("USER_NAME", username) // Ganti dengan nama pengguna sebenarnya
                startActivity(menuIntent)
                finish()
            } else {
                // Jika login gagal, tampilkan pesan
                Toast.makeText(this, "Namapengguna atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        registerText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
