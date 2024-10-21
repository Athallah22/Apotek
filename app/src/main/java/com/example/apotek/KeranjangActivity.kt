package com.example.apotek

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class KeranjangActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keranjang) // Pastikan layout untuk activity_cart.xml sudah dibuat

        sharedPreferences = getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)

        // Ambil data dari SharedPreferences
        val itemName = sharedPreferences.getString("cart_item_name", "Tidak ada item")
        val itemPrice = sharedPreferences.getString("cart_item_price", "N/A")
        val itemImageResId = sharedPreferences.getInt("cart_item_image", 0)
        val itemDescription = sharedPreferences.getString("cart_item_description", "Tidak ada deskripsi")

        // Tampilkan data di layout
        findViewById<TextView>(R.id.cartItemName).text = itemName
        findViewById<TextView>(R.id.cartItemPrice).text = itemPrice
        findViewById<ImageView>(R.id.cartItemImage).setImageResource(itemImageResId)
        findViewById<TextView>(R.id.cartItemDescription).text = itemDescription
    }
}
