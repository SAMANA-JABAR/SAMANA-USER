package com.aditprayogo.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class BantuanResponse(
    @SerializedName("status")
    val status: String?
)