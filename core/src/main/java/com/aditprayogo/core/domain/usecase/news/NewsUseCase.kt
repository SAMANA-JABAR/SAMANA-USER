package com.aditprayogo.core.domain.usecase.news

import com.aditprayogo.core.data.remote.response.Article
import com.aditprayogo.core.state.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface NewsUseCase {
    suspend fun getIndonesianNews(): Flow<ResultState<List<Article>>>
}