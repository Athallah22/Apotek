package com.example.apotek

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.apotek.databinding.MenuPesanBinding

class MenuPesanActivity : AppCompatActivity() {
    private lateinit var keranjang: ImageButton // Deklarasikan keranjang

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_pesan) // Menggunakan layout menu_pesan.xml

        // Inisialisasi ImageButton keranjang menggunakan findViewById
        keranjang = findViewById(R.id.keranjang)

        // Menambahkan listener untuk ImageButton keranjang
        keranjang.setOnClickListener {
            val intent = Intent(this, KeranjangActivity::class.java) // Pindah ke KeranjangActivity
            startActivity(intent)
        }

        // Mengatur klik untuk CardView
        findViewById<CardView>(R.id.product1).setOnClickListener {
            showDetail("Obat 1", "$15", R.drawable.bg, "Deskripsi Obat 1")
        }

        findViewById<CardView>(R.id.product2).setOnClickListener {
            showDetail("Obat 2", "$15", R.drawable.bg, "Deskripsi Obat 2")
        }

        // Tambahkan click listener untuk produk lainnya
        findViewById<CardView>(R.id.product3).setOnClickListener {
            showDetail("Obat 3", "$15", R.drawable.bg, "Deskripsi Obat 3")
        }

        findViewById<CardView>(R.id.product4).setOnClickListener {
            showDetail("Obat 4", "$15", R.drawable.bg, "Deskripsi Obat 4")
        }

        findViewById<CardView>(R.id.product5).setOnClickListener {
            showDetail("Obat 5", "$15", R.drawable.bg, "Deskripsi Obat 5")
        }

        findViewById<CardView>(R.id.product6).setOnClickListener {
            showDetail("Obat 6", "$15", R.drawable.bg, "Deskripsi Obat 6")
        }
    }

    private fun showDetail(name: String, price: String, imageResId: Int, description: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("EXTRA_NAME", name)
        intent.putExtra("EXTRA_PRICE", price)
        intent.putExtra("EXTRA_IMAGE", imageResId)
        intent.putExtra("EXTRA_DESCRIPTION", description)
        startActivity(intent)
    }
}
