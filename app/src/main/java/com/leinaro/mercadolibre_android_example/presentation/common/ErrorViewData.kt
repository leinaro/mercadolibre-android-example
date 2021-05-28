package com.leinaro.mercadolibre_android_example.presentation.common

sealed class ErrorViewData
data class DefaultError(val throwable: Throwable): ErrorViewData()