package com.arditb.cryptotodate.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "crypto_items_table")
data class CryptoItem(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @ColumnInfo(name = "currency")
    val currency: String,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "priceDate")
    val priceDate: String,

    @ColumnInfo(name = "priceTimestamp")
    val priceTimestamp: String,

    @ColumnInfo(name = "symbol")
    val symbol: String,

    @ColumnInfo(name = "circulatingSupply")
    val circulatingSupply: Int,

    @ColumnInfo(name = "maxSupply")
    val maxSupply: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "logoUrl")
    val logoUrl: String,

    @ColumnInfo(name = "marketCap")
    val marketCap: Double,

    @ColumnInfo(name = "rank")
    val rank: Int,

    @ColumnInfo(name = "high")
    val high: Double,

    @ColumnInfo(name = "highTimestamp")
    val highTimestamp: String
)