package com.example.apotek

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
        val editor = sharedPreferences.edit()
        editor.putString("cart_item_name", product.name)
        editor.putString("cart_item_price", product.price)
        editor.putInt("cart_item_image", product.imageResId)
        editor.putString("cart_item_description", product.description)
        editor.apply()

        // Menampilkan Toast untuk konfirmasi
        Toast.makeText(this, "${product.name} telah ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
    }
}
