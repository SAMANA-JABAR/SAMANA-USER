package com.aditprayogo.core.data.repository

import com.aditprayogo.core.data.remote.response.Article
import com.aditprayogo.core.domain.repository.NewsRepository
import com.aditprayogo.core.data.remote.retrofit.NewsService
import com.aditprayogo.core.state.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : NewsRepository {

    override suspend fun getIndonesianNews(): Flow<ResultState<List<Article>>> {
        return flow {
            try {
                val response = newsService.getIndonesianNews()
                val data = response.articles
                data?.let { emit(ResultState.Success(data)) }
            } catch (e : Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)
    }
}