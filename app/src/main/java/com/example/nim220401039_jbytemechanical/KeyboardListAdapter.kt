package com.example.nim220401039_jbytemechanical

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KeyboardListAdapter(private val listKeyboard: ArrayList<Keyboard>) :
    RecyclerView.Adapter<KeyboardListAdapter.ListViewHolder>() {

    var onItemClick: ((Keyboard) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.keyboard_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val keyboard = listKeyboard[position]
        holder.productImage.setImageResource(keyboard.product_image)
        holder.productName.text = keyboard.product_name
        holder.productPrice.text = keyboard.product_price

        // Klik untuk detail
        holder.itemView.setOnClickListener {
            val intent =
                Intent(holder.itemView.context, KeyboardDetailActivity::class.java).apply {
                    putExtra("EXTRA_NAME", keyboard.product_name)
                    putExtra("EXTRA_IMAGE", keyboard.product_image)
                    putExtra("EXTRA_DESCRIPTION", keyboard.product_price)
                }
            holder.itemView.context.startActivity(intent)
        }

        // Tombol Share
        holder.btnShare.setOnClickListener {
            shareContent(holder.itemView.context)
        }
    }

    private fun shareContent(context: android.content.Context) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out this Product")
            type = "text/plain"
        }
        val chooser = Intent.createChooser(shareIntent, "Share via")
        context.startActivity(chooser)
    }

    override fun getItemCount(): Int = listKeyboard.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.img_keyboard_list)
        val productName: TextView = itemView.findViewById(R.id.name_keyboard_list)
        val productPrice: TextView = itemView.findViewById(R.id.price_keyboard_list)
        val btnShare: Button = itemView.findViewById(R.id.btnShare)
    }
}
