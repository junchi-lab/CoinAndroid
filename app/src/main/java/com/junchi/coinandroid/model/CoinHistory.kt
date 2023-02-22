package com.junchi.coinandroid.model

import com.google.gson.annotations.SerializedName


data class CoinHistory(
    val status: String,
    val data: List<HistoryData>?
)

data class HistoryData(
    @SerializedName("transaction_date") val transactionDate: String,
    val type: String,
    @SerializedName("units_traded") val unitsTraded: String,
    val price: String,
    val total: String

)
