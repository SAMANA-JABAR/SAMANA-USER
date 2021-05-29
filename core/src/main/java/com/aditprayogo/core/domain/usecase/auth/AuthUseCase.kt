package com.aditprayogo.core.domain.usecase.auth

import com.aditprayogo.core.domain.model.PasswordData
import com.aditprayogo.core.domain.model.UserData
import com.aditprayogo.core.state.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface AuthUseCase {
    suspend fun login(nik : String, password : String) : Flow<ResultState<UserData>>
    suspend fun saveUserPreferences(nik : String, password : String)
    suspend fun changePassword(nik : String, currentPass : String, newPass : String) : Flow<ResultState<PasswordData>>
}