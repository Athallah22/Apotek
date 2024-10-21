package com.example.apotek

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu) // Pastikan layout yang benar

        // Inisialisasi ImageButton cariApotek
        val imageButtonGmaps: ImageButton = findViewById(R.id.cariApotek)
        imageButtonGmaps.setOnClickListener {
            // Membuka aplikasi Google Maps atau aplikasi peta lain
            val gmmIntentUri = Uri.parse("geo:0,0?q=Apotek K24")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

//            // Cek apakah ada aplikasi peta yang bisa menangani intent
//            Toast.makeText(this, mapIntent.resolveActivity(packageManager));
//            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
//            } else {
//                Toast.makeText(this, "Tidak ada aplikasi peta yang terinstal", Toast.LENGTH_SHORT).show()
//            }
        }

        // Inisialisasi ImageButton untuk gambar pesan
        val imageButtonPesan: ImageButton = findViewById(R.id.pesan)
        imageButtonPesan.setOnClickListener {
            // Intent untuk berpindah ke MenuPesanActivity yang menampilkan menu_pesan.xml
            val pesanIntent = Intent(this, MenuPesanActivity::class.java)
            startActivity(pesanIntent)
        }

        // Inisialisasi ImageButton untuk gambar pesan
        val imageButtonRiwayat: ImageButton = findViewById(R.id.riwayat)
        imageButtonRiwayat.setOnClickListener {
            // Intent untuk berpindah ke MenuPesanActivity yang menampilkan menu_pesan.xml
            val pesanIntent = Intent(this, RiwayatActivity::class.java)
            startActivity(pesanIntent)
        }
    }
}
