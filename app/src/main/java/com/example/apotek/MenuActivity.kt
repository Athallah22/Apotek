package com.example.apotek

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu) // Pastikan layout yang benar

        // Inisialisasi TextView untuk sapaan
        val welcomeText: TextView = findViewById(R.id.welcomeText)

        // Mengambil nama pengguna dari Intent
        val userName = intent.getStringExtra("USER_NAME") ?: "Pengguna"
        welcomeText.text = "Hello, $userName" // Menampilkan sapaan

        val profileButton: ImageButton = findViewById(R.id.profileButton)
        profileButton.setOnClickListener {
            val profileIntent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("USER_NAME", userName)
            }
            startActivity(profileIntent)
        }

        // Inisialisasi ImageButton cariApotek
        val imageButtonGmaps: ImageButton = findViewById(R.id.cariApotek)
        imageButtonGmaps.setOnClickListener {
            // Membuka aplikasi Google Maps atau aplikasi peta lain
            val gmmIntentUri = Uri.parse("geo:0,0?q=Apotek K24")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(mapIntent)
        }

        // Inisialisasi ImageButton untuk gambar pesan
        val imageButtonPesan: ImageButton = findViewById(R.id.pesan)
        imageButtonPesan.setOnClickListener {
            // Intent untuk berpindah ke MenuPesanActivity
            val pesanIntent = Intent(this, MenuPesanActivity::class.java)
            startActivity(pesanIntent)
        }

        // Inisialisasi ImageButton untuk gambar riwayat
        val imageButtonRiwayat: ImageButton = findViewById(R.id.riwayat)
        imageButtonRiwayat.setOnClickListener {
            // Intent untuk berpindah ke RiwayatActivity
            val riwayatIntent = Intent(this, RiwayatActivity::class.java)
            startActivity(riwayatIntent)
        }
    }
}
