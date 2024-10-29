package com.example.apotek

import CartItem
import KeranjangDB
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PembayaranActivity : AppCompatActivity() {
    private lateinit var pembayaranDB: PembayaranDB
    private lateinit var keranjangDB: KeranjangDB
    private lateinit var konfirmasiButton: Button
    private lateinit var totalPriceTextView: TextView
    private lateinit var itemPembayaranContainer: LinearLayout

    private var totalPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pembayaran)

        // Inisialisasi database
        pembayaranDB = PembayaranDB(this)
        keranjangDB = KeranjangDB(this)

        // Inisialisasi UI
        itemPembayaranContainer = findViewById(R.id.itemPembayaranContainer)
        totalPriceTextView = findViewById(R.id.totalPrice)
        konfirmasiButton = findViewById(R.id.konfirmasiButton)

        // Mengambil semua item dari keranjang
        val cartItems = keranjangDB.getAllCartItems()

        // Menampilkan semua item di UI
        displayCartItems(cartItems)

        // Mengambil tanggal saat ini
        val currentDate = getCurrentDate()

        // Menampilkan tanggal ke dalam TextView
        findViewById<TextView>(R.id.tanggalText).text = currentDate

        // Inisialisasi dan set listener untuk tombol konfirmasi
        konfirmasiButton.setOnClickListener {
            val namaPemesan = findViewById<EditText>(R.id.namaPemesanEditText).text.toString()
            val alamatPengiriman = findViewById<EditText>(R.id.alamatPengirimanEditText).text.toString()
            val nomorTelepon = findViewById<EditText>(R.id.nomorTeleponEditText).text.toString()
            val tanggal = currentDate // Menggunakan tanggal saat ini

            if (namaPemesan.isNotEmpty() && alamatPengiriman.isNotEmpty() && nomorTelepon.isNotEmpty()) {
                // Anggap jumlah obat sebagai total item di keranjang
                val jumlahObat = cartItems.size // Hitung jumlah total item di keranjang
                val result = pembayaranDB.insertPembayaran(namaPemesan, alamatPengiriman, nomorTelepon, totalPrice, tanggal, jumlahObat)
                if (result != -1L) {
                    Toast.makeText(this, "Pembayaran berhasil untuk $namaPemesan", Toast.LENGTH_SHORT).show()
                    // Setelah pembayaran berhasil, Anda bisa mengosongkan keranjang jika perlu
                    // keranjangDB.clearCart() // Uncomment jika ada method untuk menghapus keranjang
                } else {
                    Toast.makeText(this, "Gagal menyimpan data pembayaran", Toast.LENGTH_SHORT).show()
                }
                finish() // Kembali ke activity sebelumnya
            } else {
                Toast.makeText(this, "Harap lengkapi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Fungsi untuk menampilkan item-item keranjang ke dalam UI
    private fun displayCartItems(cartItems: List<Product>) { // Change type to List<CartItem> if needed
        itemPembayaranContainer.removeAllViews() // Bersihkan tampilan lama
        totalPrice = 0

        for (item in cartItems) {
            // Inflate layout item_pembayaran
            val itemView = layoutInflater.inflate(R.layout.item_pembayaran, itemPembayaranContainer, false)

            // Set detail item
            val itemImage = itemView.findViewById<ImageView>(R.id.cartItemImage)
            val itemName = itemView.findViewById<TextView>(R.id.cartItemName) // Ganti ID sesuai layout
            val itemPrice = itemView.findViewById<TextView>(R.id.cartItemPrice) // Ganti ID sesuai layout
            val itemQuantity = itemView.findViewById<TextView>(R.id.cartItemQuantity) // Ganti ID sesuai layout

            itemName.text = item.name
            itemPrice.text = "Rp. ${item.price}"
            itemQuantity.text = "1" // Jumlah item bisa ditambahkan logika

            // Hitung total harga dengan konversi ke Int jika diperlukan
            totalPrice += item.price.toInt() // Pastikan price sudah dalam format string yang bisa di-convert

            // Tambahkan item ke container
            itemPembayaranContainer.addView(itemView)
        }

        // Tampilkan total harga
        totalPriceTextView.text = "Total Harga: Rp. $totalPrice"
    }

    // Fungsi untuk mendapatkan tanggal saat ini
    private fun getCurrentDate(): String {
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        return sdf.format(java.util.Date())
    }
}
