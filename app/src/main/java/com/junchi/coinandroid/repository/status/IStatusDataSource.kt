package com.junchi.coinandroid.repository.status

import com.junchi.coinandroid.model.AllCoin

interface IStatusDataSource {
    suspend fun getStatus(): AllCoin
}