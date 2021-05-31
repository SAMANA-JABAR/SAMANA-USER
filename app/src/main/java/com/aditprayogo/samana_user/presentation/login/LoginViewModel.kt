package com.aditprayogo.samana_user.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.loader.content.Loader
import com.aditprayogo.core.domain.model.UserData
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
interface LoginViewModelContract {
    fun login(nik: String, password: String)
    suspend fun saveUserPreferences(nik : String, password: String)
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel(), LoginViewModelContract {

    private val _state = MutableLiveData<LoaderState>()
    val state get() = _state

    private val _error = MutableLiveData<String?>()
    val error get() = _error

    private val _networkError = MutableLiveData<Boolean>()
    val networkError get() = _networkError

    private val _loginData = MutableLiveData<UserData>()
    val loginData get() = _loginData

    override fun login(nik: String, password: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            authUseCase.login(nik, password).collect { data ->
                when (data) {
                    is ResultState.Success -> {
                        _loginData.postValue(data.data!!)
                        _state.value = LoaderState.HideLoading
                    }
                    is ResultState.Error -> {
                        _error.postValue(data.error)
                        _state.value = LoaderState.HideLoading
                    }
                    is ResultState.NetworkError -> _networkError.postValue(true)
                }
            }
        }
    }

    override suspend fun saveUserPreferences(nik: String, password: String) {
        authUseCase.saveUserPreferences(nik,password)
    }


}