package com.aditprayogo.core.utils

import com.aditprayogo.core.state.ResultState
import com.bumptech.glide.load.HttpException
import java.io.IOException
import java.net.ConnectException

/**
 * Created by Aditiya Prayogo.
 */

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> ResultState<T>): ResultState<T> {
    return try {
        apiCall.invoke()
    } catch (throwable : Throwable) {
        when (throwable) {
            is IOException -> ResultState.NetworkError
            is HttpException -> {
                val code = throwable.statusCode
                val errorResponse = throwable.message
                return ResultState.Error(errorResponse, code)
            }
            is ConnectException -> ResultState.NetworkError
            else -> ResultState.Error("Internal Server Error", 500)
        }
    }
}
