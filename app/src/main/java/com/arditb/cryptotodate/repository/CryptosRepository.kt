package com.arditb.cryptotodate.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.arditb.cryptotodate.database.CryptosDatabase
import com.arditb.cryptotodate.database.asCryptoItem
import com.arditb.cryptotodate.network.CryptoApi
import com.arditb.cryptotodate.network.CryptoItem
import com.arditb.cryptotodate.network.asDatabaseCrypto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptosRepository (private val database: CryptosDatabase) {

    val cryptos: LiveData<List<CryptoItem>> = Transformations.map(database.cryptoDao.getCryptoCurrencies()) {
        it.asCryptoItem()
    }

    suspend fun refreshCryptos(convert: String) {
        withContext(Dispatchers.IO){
            val data = CryptoApi.retrofitService.getCurrencies(convert).await()
            database.cryptoDao.insertAll(data.asDatabaseCrypto())
        }
    }
}