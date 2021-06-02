package com.leinaro.mercadolibre_android_example.presentation.product.handler

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.common.ViewHandler
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.product.ProductFragment
import com.leinaro.mercadolibre_android_example.presentation.product.ProductViewData
import com.leinaro.mercadolibre_android_example.presentation.product.ShowCategoryDetails

object ShowCategoryViewHandler :
    ViewHandler<ShowCategoryDetails, BaseViewModel<ProductViewData>> {
    override fun ShowCategoryDetails.perform(
        context: Any,
        viewModel: BaseViewModel<ProductViewData>,
    ) {
        when (context) {
            is ProductFragment -> {
                showCategory(context, category)
                showLoading(context, isLoading)
            }
        }
    }

    private fun showCategory(productFragment: ProductFragment, category: Category) {
        val activity = productFragment.requireActivity() as AppCompatActivity
        activity.supportActionBar?.title = category.name
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        productFragment.binding.categoryName.text = category.name
    }

    private fun showLoading(
        productFragment: ProductFragment,
        isLoading: Boolean,
    ) {
        productFragment.binding.progressBar.visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
