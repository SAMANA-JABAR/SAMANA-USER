package com.aditprayogo.core.domain.usecase.auth

import com.aditprayogo.core.domain.model.*
import com.aditprayogo.core.state.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface AuthUseCase {
    suspend fun login(nik: String, password: String): Flow<ResultState<UserData>>
    suspend fun saveUserPreferences(nik: String, password: String, nama : String)
    suspend fun changePassword(
        nik: String,
        currentPass: String,
        newPass: String
    ): Flow<ResultState<PasswordData>>

    suspend fun dashboard(nik: String): Flow<ResultState<DashboardData>>
    suspend fun getHistoryBansos(nik : String) : Flow<ResultState<List<HistoryData>>>
    suspend fun inputBantuan(
        nama: String,
        nik: String,
        tglLahir: String,
        tanggungan: String,
        pendidikan: String,
        profesi: String,
        status: String,
        gaji: String,
        kota: String,
        kecamatan: String,
        kelurahan: String,
        rt: String,
        rw: String,
        alamat: String,
        bantuan: String,
        tahap: String,
        kesehatan: String,
        atap: String,
        dinding: String,
        lantai: String,
        penerangan: String,
        air: String,
        luasRumah: String,
    ): Flow<ResultState<InputData>>

}