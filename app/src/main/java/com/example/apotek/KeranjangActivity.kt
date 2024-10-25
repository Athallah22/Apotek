package com.example.apotek

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class KeranjangActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private var totalHarga: Int = 0
    private var itemCount: Int = 1 // Jumlah item, default 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keranjang) // Pastikan layout untuk keranjang.xml sudah dibuat

        sharedPreferences = getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)

        // Ambil data dari SharedPreferences
        val itemName = sharedPreferences.getString("cart_item_name", "Tidak ada item")
        val itemPrice = sharedPreferences.getString("cart_item_price", "Rp. 0")
        val itemImageResId = sharedPreferences.getInt("cart_item_image", 0)

        // Tampilkan data di layout
        findViewById<TextView>(R.id.cartItemName).text = itemName
        findViewById<TextView>(R.id.cartItemPrice).text = itemPrice
        findViewById<ImageView>(R.id.cartItemImage).setImageResource(itemImageResId)

        // Hitung total harga
        val priceString = itemPrice?.replace("Rp. ", "")?.replace(".", "") // Menghapus simbol Rp. dan titik
        val itemPriceInt = priceString?.toIntOrNull() ?: 0 // Mengubah ke Int, default ke 0
        totalHarga = itemPriceInt * itemCount // Menghitung total harga berdasarkan jumlah item

        // Tampilkan total harga di layout
        findViewById<TextView>(R.id.totalPrice).text = "Rp.  ${totalHarga.format()}"

        // Inisialisasi tombol
        val increaseButton: ImageButton = findViewById(R.id.increaseButton)
        val decreaseButton: ImageButton = findViewById(R.id.decreaseButton)
        val removeItemButton: ImageButton = findViewById(R.id.removeItemButton)

        // Set listener untuk tombol tambah
        increaseButton.setOnClickListener {
            itemCount++
            updateTotalPrice(itemPriceInt)
        }

        // Set listener untuk tombol kurang
        decreaseButton.setOnClickListener {
            if (itemCount > 1) {
                itemCount--
                updateTotalPrice(itemPriceInt)
            }
        }

        // Set listener untuk tombol hapus item
        removeItemButton.setOnClickListener {
            // Menghapus item dari keranjang
            finish() // Kembali ke activity sebelumnya
        }
    }

    private fun updateTotalPrice(itemPriceInt: Int) {
        totalHarga = itemPriceInt * itemCount // Hitung total harga
        findViewById<TextView>(R.id.totalPrice).text = "Rp.  ${totalHarga.format()}" // Perbarui tampilan total harga
        findViewById<TextView>(R.id.banyakItem).text = "$itemCount" // Perbarui jumlah item di tampilan
    }

    // Fungsi untuk memformat angka ke format IDR
    private fun Int.format(): String {
        return String.format("%,d", this).replace(",", ".")
    }
}
