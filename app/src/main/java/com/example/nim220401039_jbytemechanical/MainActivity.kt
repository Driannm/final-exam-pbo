package com.example.nim220401039_jbytemechanical

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvKeyboard: RecyclerView
    private val list = ArrayList<Keyboard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvKeyboard = findViewById(R.id.rv_keyboard)
        rvKeyboard.setHasFixedSize(true)

        list.addAll(getKeyboard())
        showRecyclerView(isGrid = false)
    }

    private fun getKeyboard(): ArrayList<Keyboard> {
        val dataName = resources.getStringArray(R.array.product_name)
        val dataImg = resources.obtainTypedArray(R.array.product_image)
        val dataPrice = resources.getStringArray(R.array.product_price)
        val listKeyboard = ArrayList<Keyboard>()

        for (i in dataName.indices) {
            val keyboard = Keyboard(dataName[i], dataPrice[i], dataImg.getResourceId(i, -1))
            listKeyboard.add(keyboard)
        }

        dataImg.recycle() // Hindari memory leak
        return listKeyboard
    }

    private fun showRecyclerView(isGrid: Boolean) {
        rvKeyboard.layoutManager =
            if (isGrid) GridLayoutManager(this, 2) else LinearLayoutManager(this)
        val adapter = if (isGrid) KeyboardGridAdapter(list) else KeyboardListAdapter(list)
        rvKeyboard.adapter = adapter

        if (isGrid) {
            (adapter as KeyboardGridAdapter).onItemClick = { keyboard ->
                val intent = Intent(this, KeyboardDetailActivity::class.java)
                intent.putExtra("keyboard_data", keyboard)
                startActivity(intent)
            }
        }
    }

    private fun showAbout() {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    private fun showProfile() {
        startActivity(Intent(this, CiruculumVitaeActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_utama, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list -> showRecyclerView(isGrid = false)
            R.id.grid -> showRecyclerView(isGrid = true)
            R.id.about -> showAbout()
            R.id.profile -> showProfile()
        }
        return super.onOptionsItemSelected(item)
    }
}
