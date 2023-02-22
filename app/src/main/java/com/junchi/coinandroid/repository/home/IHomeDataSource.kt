package com.junchi.coinandroid.repository.home

import com.junchi.coinandroid.model.AllCoin

interface IHomeDataSource {
    suspend fun getAllCoin(): AllCoin
}