package com.aditprayogo.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("alamat")
    val alamat: String?,
    @SerializedName("bantuan")
    val bantuan: List<Bantuan>?,
    @SerializedName("nama")
    val nama: String?,
    @SerializedName("nik")
    val nik: String?,
    @SerializedName("password")
    val password: String?
)

data class Bantuan(
    @SerializedName("jenis")
    val jenis: String?,
    @SerializedName("status")
    val status: String?
)
