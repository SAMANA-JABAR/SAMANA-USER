package com.aditprayogo.core.utils.mapper

import com.aditprayogo.core.data.remote.response.AuthResponse
import com.aditprayogo.core.domain.model.User

/**
 * Created by Aditiya Prayogo.
 */
object DataMapper {

    fun mapDataDoDomain(data : AuthResponse) : User = User(nik = data.nik, password = data.password)

}