package com.aditprayogo.core.di

import com.aditprayogo.core.data.remote.retrofit.NewsService
import com.aditprayogo.core.data.remote.retrofit.config.RetrofitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}