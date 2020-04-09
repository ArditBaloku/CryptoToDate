package com.arditb.cryptotodate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arditb.cryptotodate.network.CryptoApi
import com.arditb.cryptotodate.network.CryptoApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class CryptoApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel(){

    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<CryptoApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<CryptoApiStatus>
        get() = _status


    private val _properties = MutableLiveData<List<CryptoApiStatus>>()

    val properties: LiveData<List<CryptoApiStatus>>
        get() = _properties


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCryptoData()
    }

    private fun getCryptoData(){
        val one = 1
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}