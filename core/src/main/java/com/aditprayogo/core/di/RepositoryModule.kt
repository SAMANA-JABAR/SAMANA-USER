package com.aditprayogo.core.di

import com.aditprayogo.core.domain.repository.news.NewsRepository
import com.aditprayogo.core.data.repository.news.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aditiya Prayogo.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository

}