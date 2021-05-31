package com.aditprayogo.core.utils.mapper

import com.aditprayogo.core.data.remote.response.AuthResponse
import com.aditprayogo.core.data.remote.response.BantuanResponse
import com.aditprayogo.core.data.remote.response.DashboardResponse
import com.aditprayogo.core.data.remote.response.PasswordResponse
import com.aditprayogo.core.domain.model.DashboardData
import com.aditprayogo.core.domain.model.InputData
import com.aditprayogo.core.domain.model.PasswordData
import com.aditprayogo.core.domain.model.UserData

/**
 * Created by Aditiya Prayogo.
 */
object DataMapper {

    fun mapUserDataToUserDomain(data: AuthResponse): UserData =
        UserData(nik = data.nik, password = data.password)

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

}