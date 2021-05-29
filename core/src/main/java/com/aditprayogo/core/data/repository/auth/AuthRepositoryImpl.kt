package com.aditprayogo.core.data.repository.auth

import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.data.remote.retrofit.AuthService
import com.aditprayogo.core.domain.model.PasswordData
import com.aditprayogo.core.domain.model.UserData
import com.aditprayogo.core.domain.repository.auth.AuthRepository
import com.aditprayogo.core.state.ResultState
import com.aditprayogo.core.utils.mapper.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val userPreferences: UserPreferences
) : AuthRepository {

    override suspend fun login(nik: String, password: String): Flow<ResultState<UserData>> =
        flow {
            try {
                val response = authService.login(nik, password)
                val dataMapped = DataMapper.mapUserDataToUserDomain(response)
                emit(ResultState.Success(dataMapped))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 401))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun saveUserPreferences(
        nik: String,
        password: String
    ) {
        userPreferences.saveUser(nik, password)
    }

    override suspend fun changePassword(
        nik: String,
        currentPass: String,
        newPass: String
    ): Flow<ResultState<PasswordData>> {
        return flow {
            try {
                val response = authService.changePassword(nik, currentPass, newPass)
                val dataMapped = DataMapper.mapPasswordDataToUserDomain(response)
                emit(ResultState.Success(dataMapped))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 401))
            }
        }
    }

}