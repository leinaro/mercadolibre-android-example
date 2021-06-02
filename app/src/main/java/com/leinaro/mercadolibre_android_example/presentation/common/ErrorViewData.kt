package com.leinaro.mercadolibre_android_example.presentation.common

sealed class ErrorViewData(val error: Throwable)
data class DefaultError(val throwable: Throwable) : ErrorViewData(throwable)