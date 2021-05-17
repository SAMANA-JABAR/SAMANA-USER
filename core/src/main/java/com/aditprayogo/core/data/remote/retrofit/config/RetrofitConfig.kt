package com.aditprayogo.core.data.remote.retrofit.config

import com.aditprayogo.core.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Aditiya Prayogo.
 */
object RetrofitConfig {

    fun retrofitClientBuilder(
        url: String = BuildConfig.BASE_URL,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private fun okHttpClient() = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .addInterceptor { chain ->
            var original = chain.request()

            val url = original.url
                .newBuilder()
                .addQueryParameter("apiKey", BuildConfig.API_KEY)
                .build()

            original = original.newBuilder().url(url).addHeader("Accept", "application/json").build()
            chain.proceed(original)
        }
        .addNetworkInterceptor(createLoggingInterceptor())
        .pingInterval(30, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .build()

    private fun createLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}