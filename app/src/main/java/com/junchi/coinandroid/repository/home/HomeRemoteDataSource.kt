package com.junchi.coinandroid.repository.home

import com.junchi.coinandroid.model.AllCoin
import com.junchi.coinandroid.network.ApiClient

class HomeRemoteDataSource(private val apiClient: ApiClient) : IHomeDataSource{

    override suspend fun getAllCoin(): AllCoin {
        return apiClient.getCoin()
    }
}