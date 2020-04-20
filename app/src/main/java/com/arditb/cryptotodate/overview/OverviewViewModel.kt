package com.arditb.cryptotodate.overview

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.arditb.cryptotodate.database.getDatabase
import com.arditb.cryptotodate.network.CryptoItem
import com.arditb.cryptotodate.repository.CryptosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

enum class CryptoApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPref = application.getSharedPreferences("CryptoToDateSettings", Context.MODE_PRIVATE)

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    // Repository for managing data sources
    private val cryptosRepository = CryptosRepository(getDatabase(application))

    // Crypto Items
    val cryptos = cryptosRepository.cryptos

    // Navigation data
    private val _navigateToSelectedCrypto = MutableLiveData<CryptoItem>()

    val navigateToSelectedCrypto: LiveData<CryptoItem>
        get() = _navigateToSelectedCrypto

    // Api status
    private val _status = MutableLiveData<CryptoApiStatus>()

    val status: LiveData<CryptoApiStatus>
        get() = _status


    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        val convert = sharedPref.getString("Currency Key", "USD") ?: "USD"
        coroutineScope.launch {
            try {
                cryptosRepository.refreshCryptos(convert)
                _status.value = CryptoApiStatus.DONE
            } catch (networkError: IOException) {
                _status.value = CryptoApiStatus.ERROR
            }
        }
    }

    fun displayCryptoDetails(cryptoItem: CryptoItem) {
        _navigateToSelectedCrypto.value = cryptoItem
    }

    fun displayCryptoDetailsComplete() {
        _navigateToSelectedCrypto.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}