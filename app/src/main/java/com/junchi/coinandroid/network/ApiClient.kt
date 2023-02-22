package com.junchi.coinandroid.network

import com.junchi.coinandroid.model.AllCoin
import com.junchi.coinandroid.model.CoinHistory
import com.junchi.coinandroid.model.CoinInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("public/ticker/ALL_KRW")
    suspend fun getCoin() : AllCoin

    @GET("public/assetsstatus/ALL")
    suspend fun getStatus() : AllCoin

    @GET("public/ticker/{coin}_KRW")
    suspend fun getCoinInfo(@Path("coin") coin: String) : CoinInfo

    @GET("public/transaction_history/{coin}_KRW")
    suspend fun getCoinHistory(@Path("coin") coin: String) : CoinHistory

    companion object {

        private const val baseUrl = "https://api.bithumb.com/"

        fun create(): ApiClient {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)

        }
    }
}