package com.aditprayogo.core.domain.repository.auth

import com.aditprayogo.core.data.remote.response.AuthResponse
import com.aditprayogo.core.state.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface AuthRepository {
    suspend fun login(nik : String, password : String) : Flow<ResultState<AuthResponse>>
    suspend fun saveUserPreferences(nik : String, password: String)
}