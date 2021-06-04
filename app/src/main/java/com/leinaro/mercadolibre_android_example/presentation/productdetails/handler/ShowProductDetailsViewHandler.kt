package com.leinaro.mercadolibre_android_example.presentation.productdetails.handler

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.common.ViewHandler
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import com.leinaro.mercadolibre_android_example.presentation.productdetails.PicturesAdapter
import com.leinaro.mercadolibre_android_example.presentation.productdetails.ProductDetailsFragment
import com.leinaro.mercadolibre_android_example.presentation.productdetails.ProductDetailsViewData
import com.leinaro.mercadolibre_android_example.presentation.productdetails.ShowProductDetails
import java.text.NumberFormat
import java.util.*

object ShowProductDetailsViewHandler :
    ViewHandler<ShowProductDetails, BaseViewModel<ProductDetailsViewData>> {
    override fun ShowProductDetails.perform(
        context: Any,
        viewModel: BaseViewModel<ProductDetailsViewData>,
    ) {
        when (context) {
            is ProductDetailsFragment -> {
                showProduct(context, product)
                showLoading(context, isLoading)
            }
        }
    }

    private fun showProduct(productDetailsFragment: ProductDetailsFragment, product: Product) {
        val activity = productDetailsFragment.requireActivity() as AppCompatActivity
        activity.supportActionBar?.title = product.name
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        productDetailsFragment.binding.productName.text = product.name
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("COP")

        productDetailsFragment.binding.productPrice.text = format.format(product.price?.toDouble())

        with(productDetailsFragment.binding.picturesViewPager) {
            adapter =
                PicturesAdapter(productDetailsFragment.requireContext(), product.pictures.orEmpty())
        }
    }

    private fun showLoading(
        productDetailsFragment: ProductDetailsFragment,
        isLoading: Boolean,
    ) {
        productDetailsFragment.binding.progressBar.visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}
