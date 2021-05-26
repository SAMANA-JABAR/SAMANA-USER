package com.aditprayogo.core.domain.usecase.news

import com.aditprayogo.core.data.remote.response.Article
import com.aditprayogo.core.domain.repository.news.NewsRepository
import com.aditprayogo.core.state.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class NewsUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) :
    NewsUseCase {
    override suspend fun getIndonesianNews(): Flow<ResultState<List<Article>>> {
        return newsRepository.getIndonesianNews()
    }
}