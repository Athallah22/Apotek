package com.example.apotek

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

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
            showDetail("Obat 1", "Rp. 15000", R.drawable.bg, "Deskripsi Obat 1")
        }

        findViewById<CardView>(R.id.product2).setOnClickListener {
            showDetail("Obat 2", "Rp. 15000", R.drawable.bg, "Deskripsi Obat 2")
        }

        findViewById<CardView>(R.id.product3).setOnClickListener {
            showDetail("Obat 3", "Rp. 15000", R.drawable.bg, "Deskripsi Obat 3")
        }

        findViewById<CardView>(R.id.product4).setOnClickListener {
            showDetail("Obat 4", "Rp. 15000", R.drawable.bg, "Deskripsi Obat 4")
        }

        findViewById<CardView>(R.id.product5).setOnClickListener {
            showDetail("Obat 5", "Rp. 15000", R.drawable.bg, "Deskripsi Obat 5")
        }

        findViewById<CardView>(R.id.product6).setOnClickListener {
            showDetail("Obat 6", "Rp. 15000", R.drawable.bg, "Deskripsi Obat 6")
        }

        // Menambahkan listener untuk setiap tombol "Beli"
        findViewById<Button>(R.id.beli1).setOnClickListener { addToCart("Obat 1", "$15", R.drawable.bg, "Deskripsi Obat 1") }
        findViewById<Button>(R.id.beli2).setOnClickListener { addToCart("Obat 2", "$15", R.drawable.bg, "Deskripsi Obat 2") }
        findViewById<Button>(R.id.beli3).setOnClickListener { addToCart("Obat 3", "$15", R.drawable.bg, "Deskripsi Obat 3") }
        findViewById<Button>(R.id.beli4).setOnClickListener { addToCart("Obat 4", "$15", R.drawable.bg, "Deskripsi Obat 4") }
        findViewById<Button>(R.id.beli5).setOnClickListener { addToCart("Obat 5", "$15", R.drawable.bg, "Deskripsi Obat 5") }
        findViewById<Button>(R.id.beli6).setOnClickListener { addToCart("Obat 6", "$15", R.drawable.bg, "Deskripsi Obat 6") }
    }

    private fun addToCart(name: String, price: String, imageResId: Int, description: String) {
        val editor = getSharedPreferences("cart_prefs", MODE_PRIVATE).edit()

        // Ambil set item keranjang yang sudah ada
        val cartItems = getSharedPreferences("cart_prefs", MODE_PRIVATE)
            .getStringSet("cart_items", mutableSetOf()) ?: mutableSetOf()

        // Buat string representasi item
        val cartItem = "$name;$price;$description;$imageResId"
        cartItems.add(cartItem)

        // Simpan kembali ke SharedPreferences
        editor.putStringSet("cart_items", cartItems)
        editor.apply()

        // Tampilkan Toast untuk konfirmasi
        Toast.makeText(this, "$name telah ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
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
