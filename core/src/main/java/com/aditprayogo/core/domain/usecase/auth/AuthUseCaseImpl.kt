package com.aditprayogo.core.domain.usecase.auth

import com.aditprayogo.core.domain.model.DashboardData
import com.aditprayogo.core.domain.model.HistoryData
import com.aditprayogo.core.domain.model.InputData
import com.aditprayogo.core.domain.model.PasswordData
import com.aditprayogo.core.domain.repository.auth.AuthRepository
import com.aditprayogo.core.state.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override suspend fun login(
        nik: String,
        password: String
    ) = authRepository.login(nik, password)

    override suspend fun saveUserPreferences(nik: String, password: String, nama : String) {
        authRepository.saveUserPreferences(nik, password, nama)
    }

    override suspend fun changePassword(
        nik: String,
        currentPass: String,
        newPass: String
    ): Flow<ResultState<PasswordData>> {
        return authRepository.changePassword(nik, currentPass, newPass)
    }

    override suspend fun inputBantuan(
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
        luasRumah: String
    ): Flow<ResultState<InputData>> {
        return authRepository.inputBantuan(
            nama,
            nik,
            tglLahir,
            tanggungan,
            pendidikan,
            profesi,
            status,
            gaji,
            kota,
            kecamatan,
            kelurahan,
            rt,
            rw,
            alamat,
            bantuan,
            tahap,
            kesehatan,
            atap,
            dinding,
            lantai,
            penerangan,
            air,
            luasRumah
        )
    }

    override suspend fun dashboard(nik: String): Flow<ResultState<DashboardData>> {
        return authRepository.dashboard(nik)
    }

    override suspend fun getHistoryBansos(nik: String): Flow<ResultState<List<HistoryData>>> {
        return authRepository.getHistoryBansos(nik)
    }

}