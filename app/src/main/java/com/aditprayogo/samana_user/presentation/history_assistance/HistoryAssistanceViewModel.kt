package com.aditprayogo.samana_user.presentation.history_assistance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.core.domain.model.HistoryData
import com.aditprayogo.core.domain.usecase.auth.AuthUseCase
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.core.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
interface HistoryAssistanceViewModelContract {
    fun getHistoryBansos(nik : String)
}

@HiltViewModel
class HistoryAssistanceViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel(), HistoryAssistanceViewModelContract{

    private val _state = MutableLiveData<LoaderState>()
    val state get() = _state

    private val _error = MutableLiveData<String?>()
    val error get() = _error

    private val _networkError = MutableLiveData<Boolean>()
    val networkError get() = _networkError

    private val _historyData = MutableLiveData<List<HistoryData>>()
    val historyData get() = _historyData

    override fun getHistoryBansos(nik: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            authUseCase.getHistoryBansos(nik).collect {
                when(it) {
                    is ResultState.Success -> {
                        _historyData.postValue(it.data!!)
                        _state.value = LoaderState.HideLoading
                    }
                    is ResultState.Error -> {
                        _error.postValue(it.error)
                        _state.value = LoaderState.HideLoading
                    }
                    is ResultState.NetworkError -> {
                        _networkError.postValue(true)
                    }
                }
            }
        }
    }
}