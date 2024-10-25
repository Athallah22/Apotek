package com.example.apotek

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class KeranjangActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private var totalHarga: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keranjang) // Pastikan layout untuk keranjang.xml sudah dibuat

        sharedPreferences = getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)

        // Ambil data dari SharedPreferences
        val itemName = sharedPreferences.getString("cart_item_name", "Tidak ada item")
        val itemPrice = sharedPreferences.getString("cart_item_price", "N/A")
        val itemImageResId = sharedPreferences.getInt("cart_item_image", 0)

        // Tampilkan data di layout
        findViewById<TextView>(R.id.cartItemName).text = itemName
        findViewById<TextView>(R.id.cartItemPrice).text = itemPrice
        findViewById<ImageView>(R.id.cartItemImage).setImageResource(itemImageResId)

        // Hitung total harga
        val priceString = itemPrice?.replace("Rp. ", "")?.replace(".", "") // Menghapus simbol Rp. dan titik
        val itemPriceInt = priceString?.toIntOrNull() ?: 0 // Mengubah ke Int, default ke 0
        totalHarga += itemPriceInt // Menambahkan harga item ke total harga

        // Tampilkan total harga di layout
        findViewById<TextView>(R.id.totalPrice).text = "$ ${totalHarga.format()}"
    }

    // Fungsi untuk memformat angka ke format IDR
    private fun Int.format(): String {
        return String.format("%,d", this).replace(",", ".")
    }
}
