package com.aditprayogo.core.data.repository

import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.data.remote.response.AuthResponse
import com.aditprayogo.core.data.remote.retrofit.AuthService
import com.aditprayogo.core.domain.repository.auth.AuthRepository
import com.aditprayogo.core.state.ResultState
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

    override suspend fun login(nik: String, password: String): Flow<ResultState<AuthResponse>> =
        flow {
            try {
                val response = authService.login(nik, password)
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun saveUserPreferences(nik: String, password: String) {
        userPreferences.saveUser(nik,password)
    }

}