package com.example.apotek

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class RiwayatActivity : AppCompatActivity() {
    private lateinit var riwayat: ImageButton // Deklarasikan keranjang

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.riwayat)
    }
}