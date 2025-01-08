package com.example.nim220401039_jbytemechanical

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KeyboardGridAdapter(private val listKeyboard: ArrayList<Keyboard>) :
    RecyclerView.Adapter<KeyboardGridAdapter.GridViewHolder>() {

    var onItemClick: ((Keyboard) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.keyboard_grid, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int = listKeyboard.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val keyboard = listKeyboard[position]
        holder.productImage.setImageResource(keyboard.product_image)
        holder.productName.text = keyboard.product_name
        holder.productPrice.text = keyboard.product_price

        holder.itemView.setOnClickListener {
            val intent =
                Intent(holder.itemView.context, KeyboardDetailActivity::class.java).apply {
                    putExtra("EXTRA_NAME", keyboard.product_name)
                    putExtra("EXTRA_IMAGE", keyboard.product_image)
                    putExtra("EXTRA_DESCRIPTION", keyboard.product_price)
                }
            holder.itemView.context.startActivity(intent)
        }
    }

    class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.img_keyboard_grid)
        val productName: TextView = itemView.findViewById(R.id.name_keyboard_grid)
        val productPrice: TextView = itemView.findViewById(R.id.price_keyboard_grid)
    }
}