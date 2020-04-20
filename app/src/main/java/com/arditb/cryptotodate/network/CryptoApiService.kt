package com.arditb.cryptotodate.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nomics.com/v1/"
private const val API_KEY = "4cf16806da94f462462ce5f1b7e598b2"
private const val CRYPTO_IDS = "BTC,ETH,XRP,USDT,BCH,BSV,LTC,EOS,BNB,ETNX,XTZ,OKB,LINK,ADA,LEOTOKEN,XLM,XMR," +
                               "HT,TRX,CRO,DASH,USDC,ETC,NEO,IOT,ATOM,ZEC,XEM,MKR,ONT,PAX,DOGE,BAT,VET,BTG,"
//                               "TUSD,DCR,LSK,QTUM,ALGO,HBAR,ICX,REP,ZRX,WAVES,RVN,BCD,BTM,MONA,GT,KNC,DGB,ENJ," +
//                               "DAI,MCO,THETA,OMG,GRPH,NMR,KCS,NANO,FTXTOKEN,KMD,XAUT,DX,SNX,QC,NEXO,SNT,DGD,NRG," +
//                               "HOT,SC,BLOCKSTACK,STEEM,TKX,VSYS,SEELE,ZEN,BITTORRENT,BTS,CETH,BCN,HC,SXP,EURT,QNT,REN,XVG"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface CryptoApiService {
    @GET("currencies/ticker")
    fun getCurrencies(@Query("convert") currency: String, @Query("key") key: String = API_KEY, @Query("ids") id: String = CRYPTO_IDS):  Deferred<List<CryptoItem>>
}

object CryptoApi {
    val retrofitService: CryptoApiService by lazy {
        retrofit.create(CryptoApiService::class.java)
    }
}