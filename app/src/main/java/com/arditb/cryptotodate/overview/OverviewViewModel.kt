package com.arditb.cryptotodate.overview

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.arditb.cryptotodate.network.CryptoApi
import com.arditb.cryptotodate.network.CryptoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class CryptoApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<CryptoApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<CryptoApiStatus>
        get() = _status

    private val sharedPref = application.getSharedPreferences("CryptoToDateSettings", Context.MODE_PRIVATE)

    private val _properties = MutableLiveData<List<CryptoItem>>()

    val properties: LiveData<List<CryptoItem>>
        get() = _properties



    private val _navigateToSelectedCrypto = MutableLiveData<CryptoItem>()

    val navigateToSelectedCrypto: LiveData<CryptoItem>
        get() = _navigateToSelectedCrypto


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCryptoData()
    }

    private fun getCryptoData() {
        coroutineScope.launch {
            val convert = sharedPref.getString("Currency Key", "USD") ?: "USD"
            var getCryptoDeferred = CryptoApi.retrofitService.getCurrencies(convert)
            try {
                _status.value = CryptoApiStatus.LOADING
                val listResult = getCryptoDeferred.await()
                _status.value = CryptoApiStatus.DONE
                _properties.value = listResult
            } catch (e: Exception) {
                _status.value = CryptoApiStatus.ERROR
                _properties.value = ArrayList()
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