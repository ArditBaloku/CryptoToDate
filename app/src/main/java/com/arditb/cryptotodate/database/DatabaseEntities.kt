package com.arditb.cryptotodate.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arditb.cryptotodate.network.CryptoItem

@Entity
data class DatabaseCrypto constructor(
    @PrimaryKey
    val id: String,
    val currency: String,
    val price: Float,
    val priceDate: String,
    val priceTimestamp: String,
    val symbol: String,
    val circulatingSupply: Double,
    val name: String,
    val logoUrl: String,
    val marketCap: Double,
    val rank: Int,
    val high: Double,
    val highTimestamp: String
)



fun List<DatabaseCrypto>.asCryptoItem(): List<CryptoItem> {
    return map {
        CryptoItem(
            id = it.id,
            currency = it.currency,
            price = it.price,
            priceDate = it.priceDate,
            priceTimestamp = it.priceTimestamp,
            symbol = it.symbol,
            circulatingSupply = it.circulatingSupply.toString(),
            name = it.name,
            logoUrl = it.logoUrl,
            marketCap = it.marketCap.toString(),
            rank = it.rank.toString(),
            high = it.high.toString(),
            highTimestamp = it.highTimestamp
        )
    }
}