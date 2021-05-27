package com.aditprayogo.core.data.remote.retrofit

import com.aditprayogo.core.data.remote.response.AuthResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Aditiya Prayogo.
 */
interface AuthService {

    companion object {
        private const val login_url = "https://login-dot-sharp-ring-312411.et.r.appspot.com/"
    }

    @FormUrlEncoded
    @POST(login_url)
    suspend fun login(
        @Field("nik") nik: String,
        @Field("password") password: String
    ): AuthResponse

}