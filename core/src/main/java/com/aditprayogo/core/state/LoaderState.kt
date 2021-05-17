package com.aditprayogo.core.state

sealed class LoaderState {
    object ShowLoading: LoaderState()
    object HideLoading: LoaderState()
}