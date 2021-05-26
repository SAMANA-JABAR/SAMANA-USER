package com.aditprayogo.samana_user.di

import com.aditprayogo.core.domain.usecase.auth.AuthUseCase
import com.aditprayogo.core.domain.usecase.auth.AuthUseCaseImpl
import com.aditprayogo.core.domain.usecase.news.NewsUseCase
import com.aditprayogo.core.domain.usecase.news.NewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Aditiya Prayogo.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideNewsUseCase(newsUseCaseImpl: NewsUseCaseImpl) : NewsUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideAuthUseCase(authUseCaseImpl: AuthUseCaseImpl) : AuthUseCase

}