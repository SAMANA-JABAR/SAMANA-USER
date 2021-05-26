package com.aditprayogo.core.domain.usecase.auth

import com.aditprayogo.core.data.remote.response.AuthResponse
import com.aditprayogo.core.state.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface AuthUseCase {
    suspend fun login(nik : String, password : String) : Flow<ResultState<AuthResponse>>
    suspend fun saveUserPreferences(nik : String, password : String)
}