package com.junchi.coinandroid.repository.status

import com.junchi.coinandroid.model.AllCoin
import com.junchi.coinandroid.network.ApiClient

class StatusRemoteDataSource(private val apiClient: ApiClient): IStatusDataSource {

    override suspend fun getStatus(): AllCoin {
        return apiClient.getStatus()
    }
}