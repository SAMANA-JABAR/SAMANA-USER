package com.aditprayogo.core.utils.mapper

import com.aditprayogo.core.data.remote.response.AuthResponse
import com.aditprayogo.core.data.remote.response.PasswordResponse
import com.aditprayogo.core.domain.model.PasswordData
import com.aditprayogo.core.domain.model.UserData

/**
 * Created by Aditiya Prayogo.
 */
object DataMapper {

    fun mapUserDataToUserDomain(data : AuthResponse) : UserData = UserData(nik = data.nik, password = data.password)

    fun mapPasswordDataToUserDomain(data : PasswordResponse) : PasswordData = PasswordData(status = data.status)

}