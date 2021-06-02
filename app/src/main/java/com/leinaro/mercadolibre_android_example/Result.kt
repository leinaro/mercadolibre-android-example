package com.leinaro.mercadolibre_android_example

sealed class Result<T> {
    data class Loading<T>(val value: T) : Result<T>()
    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val throwable: Throwable, val value: T? = null) : Result<T>()
}