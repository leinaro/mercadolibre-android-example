package com.leinaro.mercadolibre_android_example.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {
    private val viewData = MutableLiveData<ViewDataState<T>>()
    private val errorViewData = MutableLiveData<ViewDataState<ErrorViewData>>()

    fun getViewData(): LiveData<ViewDataState<T>> = viewData
    fun getErrorViewData(): LiveData<ViewDataState<ErrorViewData>> = errorViewData

    fun setValue(value: T, handler: ViewHandler<out T, out BaseViewModel<T>>) {
        viewData.value = ViewDataState(value, handler)
    }

    fun postValue(value: T, handler: ViewHandler<out T, out BaseViewModel<T>>) {
        viewData.postValue(ViewDataState(value, handler))
    }

    fun setErrorValue(
        value: ErrorViewData,
        handler: ViewHandler<ErrorViewData, BaseViewModel<ErrorViewData>>,
    ) {
        errorViewData.value = ViewDataState(value, handler)
    }

    open fun showError(throwable: Throwable) {
        setErrorValue(DefaultError(throwable), ShowGeneralErrorViewHandler)
    }
}