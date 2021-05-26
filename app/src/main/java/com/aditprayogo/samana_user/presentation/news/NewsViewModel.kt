package com.aditprayogo.samana_user.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.core.data.remote.response.Article
import com.aditprayogo.core.domain.usecase.news.NewsUseCase
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.core.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */

interface NewsViewModelContract {
    fun getIndonesianNews()
}

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel(),
    NewsViewModelContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState> get() = _state

    private val _resultIndonesianNewsFromApi = MutableLiveData<List<Article>>()
    val resultIndonesianNewsFromApi: LiveData<List<Article>> get() = _resultIndonesianNewsFromApi

    init {
        getIndonesianNews()
    }

    override fun getIndonesianNews() {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            newsUseCase.getIndonesianNews().collect { news ->
                _state.value = LoaderState.HideLoading
                if (news is ResultState.Success) {
                    _resultIndonesianNewsFromApi.postValue(news.data)
                }
            }
        }
    }

}
