package com.example.apotek

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apotek.databinding.DetailObatBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: DetailObatBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = DetailObatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)

        // Mengambil data dari Intent
        val name = intent.getStringExtra("EXTRA_NAME") ?: "N/A"
        val price = intent.getStringExtra("EXTRA_PRICE") ?: "N/A"
        val imageResId = intent.getIntExtra("EXTRA_IMAGE", 0)
        val description = intent.getStringExtra("EXTRA_DESCRIPTION") ?: "Tidak ada deskripsi"

        // Membuat objek Product
        val product = Product(name, price, imageResId, description)
        binding.productImageView.setImageResource(imageResId)
        binding.product = product

        // Tambahkan listener untuk tombol "Tambahkan ke Keranjang"
        binding.tocart.setOnClickListener {
            addToCart(product)
        }
    }

    private fun addToCart(product: Product) {
        val editor = sharedPreferences.edit()
        editor.putString("cart_item_name", product.name)
        editor.putString("cart_item_price", product.price)
        editor.putInt("cart_item_image", product.imageResId)
        editor.putString("cart_item_description", product.description)
        editor.apply()

        // Anda bisa menampilkan Toast atau dialog untuk konfirmasi
        Toast.makeText(this, "${product.name} telah ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
    }
}
