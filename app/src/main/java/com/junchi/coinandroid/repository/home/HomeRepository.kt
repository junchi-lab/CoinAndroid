package com.junchi.coinandroid.repository.home

import com.google.gson.Gson
import com.junchi.coinandroid.common.COIN_ADA
import com.junchi.coinandroid.model.AllCoin
import com.junchi.coinandroid.model.Coin
import com.junchi.coinandroid.model.CoinData
import timber.log.Timber

class HomeRepository(private val remoteDataSource: HomeRemoteDataSource) {

    private suspend fun getAllCoin() : AllCoin {
        return remoteDataSource.getAllCoin()
    }

    suspend fun getCoinInfo() : List<Coin> {
        val coinList = ArrayList<Coin>()
        val result = getAllCoin()

        for (coin in result.data ){
            try {
                if (coin.key == COIN_ADA)
                    break
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data[(coin.key)])
                val gsonFromJson = gson.fromJson(gsonToJson, CoinData::class.java)
                val coin = Coin(coin.key, gsonFromJson)
                coinList.add(coin)

            } catch (e: Exception){
                Timber.d(e.toString())
            }
        }
        return coinList
    }
}