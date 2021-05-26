package com.aditprayogo.core.domain.repository.news

import com.aditprayogo.core.state.ResultState
import com.aditprayogo.core.data.remote.response.Article
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface NewsRepository {
    suspend fun getIndonesianNews() : Flow<ResultState<List<Article>>>
}