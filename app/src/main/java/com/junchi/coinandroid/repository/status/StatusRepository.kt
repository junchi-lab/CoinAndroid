package com.junchi.coinandroid.repository.status

import com.google.gson.Gson
import com.junchi.coinandroid.common.COIN_ADA
import com.junchi.coinandroid.model.*
import timber.log.Timber

class StatusRepository(private val remoteDataSource: StatusRemoteDataSource){

    private suspend fun getAllCoin() : AllCoin {
        return remoteDataSource.getStatus()
    }

    suspend fun getCoinStatus() : List<CoinStatus> {
        val statusList = ArrayList<CoinStatus>()
        val result = getAllCoin()

        for (coin in result.data){
            try {
                if (coin.key == COIN_ADA)
                    break
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data[(coin.key)])
                val gsonFromJson = gson.fromJson(gsonToJson, Status::class.java)
                val coinStatus = CoinStatus(coin.key, gsonFromJson)
                statusList.add(coinStatus)

            } catch (e: Exception){
                Timber.d(e.toString())
            }
        }
        return statusList
    }
}