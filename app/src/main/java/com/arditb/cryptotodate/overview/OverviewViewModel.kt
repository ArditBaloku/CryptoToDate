package com.arditb.cryptotodate.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arditb.cryptotodate.network.CryptoApi
import com.arditb.cryptotodate.network.CryptoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class CryptoApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<CryptoApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<CryptoApiStatus>
        get() = _status


    private val _properties = MutableLiveData<List<CryptoItem>>()

    val properties: LiveData<List<CryptoItem>>
        get() = _properties


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCryptoData()
    }

    private fun getCryptoData() {
        coroutineScope.launch {
            var getCryptoDeferred = CryptoApi.retrofitService.getCurrencies()
            try {
                _status.value = CryptoApiStatus.LOADING
                val listResult = getCryptoDeferred.await()
                _status.value = CryptoApiStatus.DONE
                _properties.value = listResult
                Log.i("internet", listResult[0].name)
            } catch (e: Exception) {
                _status.value = CryptoApiStatus.ERROR
                Log.i("internet", e.toString())
                _properties.value = ArrayList()
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}