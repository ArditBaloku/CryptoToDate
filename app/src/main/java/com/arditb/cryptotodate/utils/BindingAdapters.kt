package com.arditb.cryptotodate.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arditb.cryptotodate.network.CryptoItem
import com.arditb.cryptotodate.overview.CryptoItemAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CryptoItem>?){
    val adapter = recyclerView.adapter as CryptoItemAdapter
    adapter.submitList(data)
}