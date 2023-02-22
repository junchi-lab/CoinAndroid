package com.junchi.coinandroid.repository.detail

import com.junchi.coinandroid.model.CoinHistory
import com.junchi.coinandroid.model.CoinInfo

interface IDetailDataSource {

    suspend fun getCoinHistory(coin: String): CoinHistory
    suspend fun getCoinInfo(coin: String): CoinInfo

}