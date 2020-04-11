package com.arditb.cryptotodate.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arditb.cryptotodate.network.CryptoItem

class DetailViewModelFactory(
    private val cryptoItem: CryptoItem,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(cryptoItem, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}