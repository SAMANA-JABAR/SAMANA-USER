package com.aditprayogo.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class HistoryResponseItem(
    @SerializedName("jenis")
    val jenis: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tahap")
    val tahap: String?,
    @SerializedName("tanggal")
    val tanggal: String?,
    @SerializedName("validasi")
    val validasi: String?
)