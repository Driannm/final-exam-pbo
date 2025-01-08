package com.example.nim220401039_jbytemechanical

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KeyboardDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        // Ambil data yang dikirim melalui Intent
        val name = intent.getStringExtra("EXTRA_NAME")
        val imageResId = intent.getIntExtra(
            "EXTRA_IMAGE",
            R.drawable.logo
        )

        // Inisialisasi View
        val nameTextView: TextView = findViewById(R.id.tv_detailTitle)
        val imageView: ImageView = findViewById(R.id.iv_detailImage)
        val prev1ImageView: ImageView =
            findViewById(R.id.iv_imgPreview1)
        val prev2ImageView: ImageView =
            findViewById(R.id.iv_imgPreview2)
        val prev3ImageView: ImageView =
            findViewById(R.id.iv_imgPreview3)
        val specTextView: TextView = findViewById(R.id.tv_detailDesc2)
        val backButton: Button = findViewById(R.id.btn_back)
        val buyButton: Button = findViewById(R.id.btn_buy)

        // Set data ke TextView dan ImageView
        nameTextView.text = name
        imageView.setImageResource(imageResId)

        // Tentukan gambar preview dan spesifikasi berdasarkan nama produk
        val (previewImages, spec) = when (name) {
            "Corgi Fairlady Alice" -> {
                Pair(
                    arrayOf(
                        R.drawable.corgi_prev_1,
                        R.drawable.corgi_prev_2,
                        R.drawable.corgi_prev_3
                    ),
                    getString(R.string.corgi_spec)
                )
            }

            "ALTAIR-X" -> {
                Pair(
                    arrayOf(
                        R.drawable.altair_prev_1,
                        R.drawable.altair_prev_2,
                        R.drawable.altair_prev_3
                    ),
                    getString(R.string.altair_spec)
                )
            }

            "Tiger Lite Gaming" -> {
                Pair(
                    arrayOf(
                        R.drawable.tiger_prev_1,
                        R.drawable.tiger_prev_2,
                        R.drawable.tiger_prev_3
                    ),
                    getString(R.string.tiger_spec)
                )
            }

            "KBD8X MKIII" -> {
                Pair(
                    arrayOf(
                        R.drawable.kbd8x_prev_1,
                        R.drawable.kbd8x_prev_2,
                        R.drawable.kbd8x_prev_3
                    ),
                    getString(R.string.kbd8x_spec)
                )
            }

            "Tofu65 2.0" -> {
                Pair(
                    arrayOf(
                        R.drawable.tofu65_prev_1,
                        R.drawable.tofu65_prev_2,
                        R.drawable.tofu65_prev_3
                    ),
                    getString(R.string.tofu65_spec)
                )
            }

            "Yakeylt Keycaps Set" -> {
                Pair(
                    arrayOf(
                        R.drawable.tut_yakeylt_prev_1,
                        R.drawable.tut_yakeylt_prev_2,
                        R.drawable.tut_yakeylt_prev_3
                    ),
                    getString(R.string.yakeylt_spec)
                )
            }

            "Circus Keycaps Set" -> {
                Pair(
                    arrayOf(
                        R.drawable.circus_prev_1,
                        R.drawable.circus_prev_2,
                        R.drawable.circus_prev_3
                    ),
                    getString(R.string.circus_spec)
                )
            }

            "National Rhyme Keycaps Set" -> {
                Pair(
                    arrayOf(
                        R.drawable.national_rhyme_prev_1,
                        R.drawable.national_rhyme_prev_2,
                        R.drawable.national_rhyme_prev_3
                    ),
                    getString(R.string.national_spec)
                )
            }

            "Night Owl Keycaps Set" -> {
                Pair(
                    arrayOf(
                        R.drawable.night_prev_1,
                        R.drawable.night_prev_2,
                        R.drawable.night_prev_3
                    ),
                    getString(R.string.night_spec)
                )
            }

            "80Retros GAME1989" -> {
                Pair(
                    arrayOf(
                        R.drawable.retros_prev_1,
                        R.drawable.retros_prev_2,
                        R.drawable.retros_prev_3
                    ),
                    getString(R.string.retros_spec)
                )
            }

            else -> {
                Pair(
                    arrayOf(R.drawable.logo, R.drawable.logo, R.drawable.logo),
                    "Spesifikasi tidak tersedia atau belum ditambahkan"
                )
            }
        }

        // Set gambar preview ke ImageView dan spesifikasi ke TextView
        prev1ImageView.setImageResource(previewImages[0]) // Gambar preview pertama
        prev2ImageView.setImageResource(previewImages[1]) // Gambar preview kedua
        prev3ImageView.setImageResource(previewImages[2]) // Gambar preview ketiga
        specTextView.text = spec // Menampilkan spesifikasi

        // 2. Menangani Button Back
        backButton.setOnClickListener {
            finish()
        }

        // 3. Menangani Button Buy dan Menampilkan Dialog Konfirmasi
        buyButton.setOnClickListener {
            showBuyConfirmationDialog(name)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showBuyConfirmationDialog(productName: String?) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi Pembelian")
            .setMessage("Apakah anda yakin ingin membeli $productName?")
            .setPositiveButton("Yes") { _, _ ->
                // Tindakan jika pengguna memilih "Yes"
                showSuccessDialog(productName)
            }
            .setNegativeButton("No") { dialog, _ ->
                // Tutup dialog jika pengguna memilih "No"
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    private fun showSuccessDialog(productName: String?) {
        AlertDialog.Builder(this)
            .setTitle("Pembelian Berhasil")
            .setMessage("Terima Kasih Telah Membeli $productName!")
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}