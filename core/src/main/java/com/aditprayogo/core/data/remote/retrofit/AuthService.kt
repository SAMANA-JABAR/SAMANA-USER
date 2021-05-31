package com.aditprayogo.core.data.remote.retrofit

import com.aditprayogo.core.data.remote.response.*
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
        private const val input_bantuan_url = "https://inputbantuan-dot-sharp-ring-312411.et.r.appspot.com/"
        private const val dashboard_url = "https://dashboard-dot-sharp-ring-312411.et.r.appspot.com/"
        private const val history_bansos_url = "https://history-dot-sharp-ring-312411.et.r.appspot.com/"
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
     * get history bansos
     */
    @FormUrlEncoded
    @POST(history_bansos_url)
    suspend fun getHistoryBansos(
        @Field("nik") nik : String,
    ) : HistoryResponse

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

    /**
     * Dashboard url
     */
    @FormUrlEncoded
    @POST(dashboard_url)
    suspend fun dashboard(
        @Field("nik") nik : String,
    ) : DashboardResponse

    /**
     * Input bantuan url
     */
    @FormUrlEncoded
    @POST(input_bantuan_url)
    suspend fun inputBantuan(
        @Field("nama") nama : String,
        @Field("nik") nik : String,
        @Field("tgl_lahir") tglLahir : String,
        @Field("tanggungan") tanggungan : String,
        @Field("pendidikan") pendidikan : String,
        @Field("profesi") profesi : String,
        @Field("status") status : String,
        @Field("gaji") gaji : String,
        @Field("kota") kota : String,
        @Field("kecamatan") kecamatan : String,
        @Field("kelurahan") kelurahan : String,
        @Field("rt") rt : String,
        @Field("rw") rw : String,
        @Field("alamat") alamat : String,
        @Field("bantuan") bantuan : String,
        @Field("tahap") tahap : String,
        @Field("kesehatan") kesehatan : String,
        @Field("atap") atap : String,
        @Field("dinding") dinding : String,
        @Field("lantai") lantai : String,
        @Field("penerangan") penerangan : String,
        @Field("air") air : String,
        @Field("luas_rumah") luasRumah : String,
    ) : BantuanResponse


}