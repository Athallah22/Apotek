package com.example.apotek

import KeranjangDB
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apotek.databinding.DetailObatBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var cartIcon: ImageButton
    private lateinit var binding: DetailObatBinding
    private lateinit var keranjangDB: KeranjangDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = DetailObatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi database
        keranjangDB = KeranjangDB(this)

        // Mengambil data dari Intent
        val name = intent.getStringExtra("EXTRA_NAME") ?: "N/A"
        val price = intent.getStringExtra("EXTRA_PRICE") ?: "N/A"
        val imageResId = intent.getIntExtra("EXTRA_IMAGE", 0)
        val description = intent.getStringExtra("EXTRA_DESCRIPTION") ?: "Tidak ada deskripsi"

        val product = Product(name, price, imageResId, description)
        binding.productImageView.setImageResource(imageResId)
        binding.product = product

        binding.buynow.setOnClickListener {
            addToCart(product)
        }

        // Initialize cartIcon and set click listener
        cartIcon = findViewById(R.id.cart)
        cartIcon.setOnClickListener {
            // Start the KeranjangActivity
            val intent = Intent(this, KeranjangActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addToCart(product: Product) {
        val result = keranjangDB.addItemToCart(product)
        if (result != -1L) {  // Check if the insertion was successful (returns row ID)
            Toast.makeText(this, "${product.name} telah ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Gagal menambahkan ke keranjang", Toast.LENGTH_SHORT).show()
        }
    }

}

