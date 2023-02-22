package com.junchi.coinandroid.model

import com.google.gson.annotations.SerializedName


data class Status(
    @SerializedName("withdrawal_status") val withdrawal_status: Int,
    @SerializedName("deposit_status") val deposit_status: Int
)


