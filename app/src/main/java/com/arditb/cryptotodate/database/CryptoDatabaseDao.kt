package com.arditb.cryptotodate.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CryptoDatabaseDao {

    @Insert
    fun insert(crypto: CryptoItem)

    @Update
    fun update(crypto: CryptoItem)

    @Query("SELECT * from crypto_items_table WHERE id = :cryptoId")
    fun get(cryptoId:String): CryptoItem?

    @Query("DELETE FROM crypto_items_table")
    fun clear()
}