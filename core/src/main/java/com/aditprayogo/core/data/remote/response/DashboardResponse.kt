package com.aditprayogo.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class DashboardResponse(
    @SerializedName("bantuan")
    val bantuan: String?,
    @SerializedName("nama")
    val nama: String?,
    @SerializedName("nik")
    val nik: String?,
    @SerializedName("status")
    val status: String?
)