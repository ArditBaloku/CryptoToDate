package com.arditb.cryptotodate.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arditb.cryptotodate.network.CryptoItem

class DetailViewModel (cryptoItem: CryptoItem, app: Application) : AndroidViewModel(app) {

    private val _selectedCrypto = MutableLiveData<CryptoItem>()
    val selectedCrypto: LiveData<CryptoItem>
        get() = _selectedCrypto


    init {
        _selectedCrypto.value = cryptoItem
    }
}