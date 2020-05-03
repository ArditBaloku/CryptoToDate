package com.arditb.cryptotodate.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.arditb.cryptotodate.database.getDatabase
import com.arditb.cryptotodate.repository.CryptosRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "com.arditb.cryptotodate.work.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = CryptosRepository(database)
        try {
            repository.refreshCryptos()
            Log.i("RefreshDataWorker", "Work request for sync is run")
        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }
}