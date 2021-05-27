package com.leinaro.mercadolibre_android_example.presentation.product

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leinaro.mercadolibre_android_example.Result
import com.leinaro.mercadolibre_android_example.domain.usecase.get_category.GetCategoryInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val getCategoryInteractor: GetCategoryInteractor,
) : ViewModel() {

    init {
        //  getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            when (val result = getCategoryInteractor.execute()) {
                is Result.Success -> {
                    Log.e("iarl", "---" + result.value)
                }
                is Result.Failure -> {
                    Log.e("iarl", "---" + result.throwable.printStackTrace())

                }
            }
        }
    }

}
