package com.aditprayogo.core.di

import android.content.Context
import com.aditprayogo.core.data.UserPreferences
import com.aditprayogo.core.data.remote.retrofit.AuthService
import com.aditprayogo.core.data.remote.retrofit.NewsService
import com.aditprayogo.core.data.remote.retrofit.config.RetrofitAuthConfig
import com.aditprayogo.core.data.remote.retrofit.config.RetrofitConfig
import com.aditprayogo.core.data.repository.auth.AuthRepositoryImpl
import com.aditprayogo.core.domain.repository.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aditiya Prayogo.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNewsService(): NewsService {
        return RetrofitConfig.retrofitClientBuilder()
            .create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthService(): AuthService {
        return RetrofitAuthConfig.retrofitClient()
            .create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context) : UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
        userPreferences: UserPreferences
    ) : AuthRepository = AuthRepositoryImpl(authService, userPreferences)

}