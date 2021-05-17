package com.aditprayogo.core.data.remote.retrofit

import com.aditprayogo.core.data.remote.response.News
import retrofit2.http.GET

/**
 * Created by Aditiya Prayogo.
 */
interface NewsService {

    @GET("top-headlines?country=id")
    suspend fun getIndonesianNews() : News

}