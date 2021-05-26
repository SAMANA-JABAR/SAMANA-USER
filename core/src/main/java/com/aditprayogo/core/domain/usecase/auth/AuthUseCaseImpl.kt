package com.aditprayogo.core.domain.usecase.auth

import com.aditprayogo.core.domain.repository.auth.AuthRepository
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {

    override suspend fun login(nik: String, password: String) = authRepository.login(nik, password)

    override suspend fun saveUserPreferences(nik: String, password: String) {
        authRepository.saveUserPreferences(nik,password)
    }

}