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
        showRecyclerList()
    }

    private fun getKeyboard(): ArrayList<Keyboard> {
        val dataName = resources.getStringArray(R.array.product_name)
        val dataImg = resources.obtainTypedArray(R.array.product_image)
        val dataPrice = resources.getStringArray(R.array.product_price)
        val listKeyboard = ArrayList<Keyboard>()
        for (i in dataName.indices) {
            val keyboard =
                Keyboard(dataName[i], dataPrice[i], dataImg.getResourceId(i, -1))
            listKeyboard.add(keyboard)
        }
        return listKeyboard
    }

    private fun showRecyclerList() {
        rvKeyboard.layoutManager = LinearLayoutManager(this)
        val listKeyboardAdapter = KeyboardListAdapter(list)
        rvKeyboard.adapter = listKeyboardAdapter
    }

    private fun showRecyclerGrid() {
        rvKeyboard.layoutManager = GridLayoutManager(this, 2)
        val GridKeyboardAdapter = KeyboardGridAdapter(list)
        rvKeyboard.adapter = GridKeyboardAdapter

        GridKeyboardAdapter.onItemClick = { keyboard ->
            // Menavigasi ke FlowchartDetailActivity dan mengirim data flowchart
            val intent = Intent(this, KeyboardDetailActivity::class.java)
            intent.putExtra("keyboard_data", keyboard)
            startActivity(intent)
        }
    }

    private fun showAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun showProfile() {
        val intent = Intent(this, CiruculumVitaeActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_utama, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.list -> {
                showRecyclerList()
            }

            R.id.grid -> {
                showRecyclerGrid()
            }

            R.id.about -> {
                showAbout()
            }

            R.id.profile -> {
                showProfile()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}