package com.junchi.coinandroid.model

data class AllCoin(
    val status: String,
    val data: Map<String, Any>
)

data class Coin(
    val name: String,
    val info: CoinData
)

data class CoinStatus(
    val name: String,
    val status: Status
)


