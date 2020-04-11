package com.arditb.cryptotodate.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CryptoItem(
    val id: String,
    val currency: String,
    val price: Float,
    @Json(name = "price_date") val priceDate: String,
    @Json(name = "price_timestamp") val priceTimestamp: String,
    val symbol: String,
    @Json(name = "circulating_supply") val circulatingSupply: String,
    val name: String,
    @Json(name = "logo_url") val logoUrl: String,
    @Json(name = "market_cap") val marketCap: String,
    val rank: String,
    val high: String,
    @Json(name = "high_timestamp") val highTimestamp: String
) : Parcelable{}