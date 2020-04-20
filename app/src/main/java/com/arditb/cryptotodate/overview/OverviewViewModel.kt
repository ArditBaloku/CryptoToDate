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

    // Repository for managing different data sources
    private val cryptosRepository = CryptosRepository(getDatabase(application))

    // Reference to repository data
    val cryptos = cryptosRepository.cryptos

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val sharedPref = application.getSharedPreferences("CryptoToDateSettings", Context.MODE_PRIVATE)



    private val _navigateToSelectedCrypto = MutableLiveData<CryptoItem>()

    val navigateToSelectedCrypto: LiveData<CryptoItem>
        get() = _navigateToSelectedCrypto

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
                if (cryptos.value.isNullOrEmpty())
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