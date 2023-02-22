package com.junchi.coinandroid.repository.detail

import com.junchi.coinandroid.model.CoinHistory
import com.junchi.coinandroid.model.CoinInfo

class DetailRepository(private val remoteDataSource: DetailRemoteDataSource) {
    suspend fun getCoinHistory(name: String): CoinHistory{
        return remoteDataSource.getCoinHistory(name)
    }

    suspend fun getCoinInfo(name: String): CoinInfo{
        return remoteDataSource.getCoinInfo(name)
    }
}