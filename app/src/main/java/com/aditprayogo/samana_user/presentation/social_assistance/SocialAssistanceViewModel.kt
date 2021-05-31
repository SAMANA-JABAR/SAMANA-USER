package com.aditprayogo.samana_user.presentation.social_assistance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.core.domain.model.DashboardData
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
interface SocialAssistanceViewModelContract {
    fun getUserDashboard(nik : String)
}

@HiltViewModel
class SocialAssistanceViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel(), SocialAssistanceViewModelContract{

    private val _state = MutableLiveData<LoaderState>()
    val state get() = _state

    private val _error = MutableLiveData<String?>()
    val error get() = _error

    private val _networkError = MutableLiveData<Boolean>()
    val networkError get() = _networkError

    private val _dashboardData = MutableLiveData<DashboardData>()
    val dashboardData get() = _dashboardData

    override fun getUserDashboard(nik: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            authUseCase.dashboard(nik).collect {  data ->
                when(data) {
                    is ResultState.Success -> {
                        _dashboardData.postValue(data.data!!)
                        _state.value = LoaderState.HideLoading
                    }
                    is ResultState.Error -> {
                        _error.postValue(data.error)
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