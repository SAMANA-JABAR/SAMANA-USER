package com.aditprayogo.samana_user.presentation.change_profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.core.domain.model.PasswordData
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
interface ChangePasswordViewModelContract {
    fun changePassword(nik: String, currentPass: String, newPass: String)
}

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel(), ChangePasswordViewModelContract {

    private val _state = MutableLiveData<LoaderState>()
    val state get() = _state

    private val _error = MutableLiveData<String?>()
    val error get() = _error

    private val _networkError = MutableLiveData<Boolean>()
    val networkError get() = _networkError

    private val _passwordData = MutableLiveData<PasswordData>()
    val passwordData get() = _passwordData

    override fun changePassword(
        nik: String,
        currentPass: String,
        newPass: String
    ) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            authUseCase.changePassword(nik, currentPass, newPass).collect { data ->
                when(data) {
                    is ResultState.Success -> {
                        _passwordData.postValue(data.data)
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