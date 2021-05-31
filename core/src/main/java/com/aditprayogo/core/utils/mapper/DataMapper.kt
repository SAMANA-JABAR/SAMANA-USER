package com.aditprayogo.core.utils.mapper

import com.aditprayogo.core.data.remote.response.*
import com.aditprayogo.core.domain.model.*

/**
 * Created by Aditiya Prayogo.
 */
object DataMapper {

    fun mapUserDataToUserDomain(data: AuthResponse): UserData =
        UserData(nik = data.nik, password = data.password, nama = data.nama)

    fun mapPasswordDataToUserDomain(data: PasswordResponse): PasswordData =
        PasswordData(status = data.status)

    fun mapInputDataResponseToDomain(data: BantuanResponse): InputData =
        InputData(status = data.status)

    fun mapDashboardResponseToDomain(data: DashboardResponse): DashboardData =
        DashboardData(
            bantuan = data.bantuan,
            nama = data.nama,
            nik = data.nik,
            status = data.status
        )

    fun mapHistoryResponseToDataDomain(data: List<HistoryResponseItem>): List<HistoryData> =
        data.map {
            HistoryData(
                jenis = it.jenis,
                status = it.status,
                tahap = it.tahap,
                tanggal = it.tanggal,
                validasi = it.validasi
            )
        }

}