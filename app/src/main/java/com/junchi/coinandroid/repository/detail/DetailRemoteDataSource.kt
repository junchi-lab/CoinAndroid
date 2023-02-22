package com.junchi.coinandroid.repository.detail

import com.junchi.coinandroid.network.ApiClient

class DetailRemoteDataSource(private val apiClient: ApiClient) : IDetailDataSource {

    override suspend fun getCoinHistory(coin: String) = apiClient.getCoinHistory(coin)
    override suspend fun getCoinInfo(coin: String) = apiClient.getCoinInfo(coin)

}