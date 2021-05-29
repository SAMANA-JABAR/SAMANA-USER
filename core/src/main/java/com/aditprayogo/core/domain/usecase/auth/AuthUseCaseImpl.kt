package com.aditprayogo.core.domain.usecase.auth

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

    override suspend fun saveUserPreferences(nik: String, password: String) {
        authRepository.saveUserPreferences(nik,password)
    }

    override suspend fun changePassword(
        nik: String,
        currentPass: String,
        newPass: String
    ): Flow<ResultState<PasswordData>> {
        return authRepository.changePassword(nik, currentPass, newPass)
    }

}