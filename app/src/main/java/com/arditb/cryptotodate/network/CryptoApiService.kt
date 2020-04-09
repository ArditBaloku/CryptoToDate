package com.arditb.cryptotodate.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.nomics.com/v1/"
private const val API_KEY = "a4cf16806da94f462462ce5f1b7e598b2"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface CryptoApiService {
    @GET("currencies/ticker?key=4cf16806da94f462462ce5f1b7e598b2&ids=BTC,ETH,XRP&interval=1d,30d&convert=EUR")
    fun getCurrencies():  Deferred<List<CryptoItem>>
}

object CryptoApi {
    val retrofitService: CryptoApiService by lazy {
        retrofit.create(CryptoApiService::class.java)
    }
}