package com.leinaro.mercadolibre_android_example.presentation.commons

typealias ViewDataState<T> = Pair<T, ViewHandler<out T, out BaseViewModel<T>>>

interface ViewHandler<T, C> {
    fun T.perform(context: Any, viewModel: C)
}