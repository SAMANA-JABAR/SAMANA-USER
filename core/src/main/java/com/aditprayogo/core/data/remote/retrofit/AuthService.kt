package com.aditprayogo.core.data.remote.retrofit

import com.aditprayogo.core.data.remote.response.AuthResponse
import com.aditprayogo.core.data.remote.response.PasswordResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Aditiya Prayogo.
 */
interface AuthService {

    companion object {
        private const val login_url = "https://login-dot-sharp-ring-312411.et.r.appspot.com/"
        private const val change_password_url = "https://changepass-dot-sharp-ring-312411.et.r.appspot.com/"
    }

    /**
     * Login service
     */
    @FormUrlEncoded
    @POST(login_url)
    suspend fun login(
        @Field("nik") nik: String,
        @Field("password") password: String
    ): AuthResponse

    /**
     * Change password service
     */
    @FormUrlEncoded
    @POST(change_password_url)
    suspend fun changePassword(
        @Field("nik") nik : String,
        @Field("currentpass") currentPass : String,
        @Field("newpass") newPass : String
    ) : PasswordResponse



}