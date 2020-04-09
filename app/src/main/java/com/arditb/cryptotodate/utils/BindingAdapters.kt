package com.arditb.cryptotodate.utils

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.network.CryptoItem
import com.arditb.cryptotodate.overview.CryptoApiStatus
import com.arditb.cryptotodate.overview.CryptoItemAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imageUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imageView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CryptoItem>?){
    val adapter = recyclerView.adapter as CryptoItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("cryptoApiStatus")
fun bindStatus(statusImageView: ImageView, status: CryptoApiStatus?){
    when (status) {
        CryptoApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CryptoApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        CryptoApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}