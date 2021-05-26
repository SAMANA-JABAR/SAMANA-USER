package com.aditprayogo.core.data.remote.retrofit.config

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Aditiya Prayogo.
 */
object RetrofitAuthConfig {

    private val login_url = "https://appspot.com"

    fun retrofitClient(
        url: String = login_url
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
    }

    private fun okHttpClient() = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .addNetworkInterceptor(loggingInterceptor())
        .pingInterval(30, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .build()

    private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


}