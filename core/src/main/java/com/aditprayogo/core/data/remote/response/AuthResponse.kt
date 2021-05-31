package com.aditprayogo.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("nik")
    val nik: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nama")
    val nama: String
)

