package com.aditprayogo.samana_user.presentation.input_assistance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.core.domain.model.InputData
import com.aditprayogo.core.domain.usecase.auth.AuthUseCase
import com.aditprayogo.core.state.LoaderState
import com.aditprayogo.core.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.internal.aggregatedroot.codegen._com_aditprayogo_samana_user_BaseApp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
interface InputAssistanceViewModelContract {
    fun inputDataBantuan(
        nama: String,
        nik: String,
        tglLahir: String,
        tanggungan: String,
        pendidikan: String,
        profesi: String,
        status: String,
        gaji: String,
        kota: String,
        kecamatan: String,
        kelurahan: String,
        rt: String,
        rw: String,
        alamat: String,
        bantuan: String,
        tahap: String,
        kesehatan: String,
        atap: String,
        dinding: String,
        lantai: String,
        penerangan: String,
        air: String,
        luasRumah: String,
    )
}

@HiltViewModel
class InputAssistanceViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel(), InputAssistanceViewModelContract {

    private val _state = MutableLiveData<LoaderState>()
    val state get() = _state

    private val _error = MutableLiveData<String?>()
    val error get() = _error

    private val _networkError = MutableLiveData<Boolean>()
    val networkError get() = _networkError

    private val _inputData = MutableLiveData<InputData>()
    val inputData get() = _inputData

    override fun inputDataBantuan(
        nama: String,
        nik: String,
        tglLahir: String,
        tanggungan: String,
        pendidikan: String,
        profesi: String,
        status: String,
        gaji: String,
        kota: String,
        kecamatan: String,
        kelurahan: String,
        rt: String,
        rw: String,
        alamat: String,
        bantuan: String,
        tahap: String,
        kesehatan: String,
        atap: String,
        dinding: String,
        lantai: String,
        penerangan: String,
        air: String,
        luasRumah: String
    ) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            authUseCase.inputBantuan(
                nama,
                nik,
                tglLahir,
                tanggungan,
                pendidikan,
                profesi,
                status,
                gaji,
                kota,
                kecamatan,
                kelurahan,
                rt,
                rw,
                alamat,
                bantuan,
                tahap,
                kesehatan,
                atap,
                dinding,
                lantai,
                penerangan,
                air,
                luasRumah
            ).collect { data ->
                when(data) {
                    is ResultState.Success -> {
                        _inputData.postValue(data.data!!)
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