package com.arditb.cryptotodate.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arditb.cryptotodate.R
import com.arditb.cryptotodate.network.CryptoItem
import com.arditb.cryptotodate.overview.CryptoApiStatus
import com.arditb.cryptotodate.overview.CryptoItemAdapter
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        GlideToVectorYou
            .init()
            .with(imageView.context)
            .setPlaceHolder(R.drawable.loading_animation, R.drawable.ic_broken_image)
            .load(Uri.parse(imageUrl), imageView)
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