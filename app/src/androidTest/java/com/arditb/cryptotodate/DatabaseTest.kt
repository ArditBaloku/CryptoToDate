package com.arditb.cryptotodate

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arditb.cryptotodate.database.CryptoDao
import com.arditb.cryptotodate.database.CryptosDatabase
import com.arditb.cryptotodate.database.DatabaseCrypto
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var cryptoDao: CryptoDao
    private lateinit var db: CryptosDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, CryptosDatabase::class.java)
            .build()
        cryptoDao = db.cryptoDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetCrypto() {
        val item = DatabaseCrypto(
            "BTC",
            "Bitcoin",
            100.0f,
            "20.04.2020",
            "20.04.2020 abc",
            "BTC",
            123.0,
            "Bitcoin",
            "google.com",
            300.0,
            4,
            4.33,
            "string"

        )
        db.cryptoDao.insertAll(listOf(item))
        val allCryptos = db.cryptoDao.getCryptos()
            Assert.assertEquals("Bitcoin", allCryptos.first().name)
    }
}