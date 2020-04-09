package com.arditb.cryptotodate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.network.CryptoItem

class CryptoItemAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<CryptoItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
         val layoutInflater = LayoutInflater.from(parent.context)
         val view = layoutInflater.inflate(R.layout.crypto_item_template, parent, false) as TextView
         return TextItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
         val item = data[position]
         holder.textView.text = item.name
    }

}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)