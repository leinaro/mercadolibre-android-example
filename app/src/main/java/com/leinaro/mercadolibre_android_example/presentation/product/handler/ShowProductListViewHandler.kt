package com.leinaro.mercadolibre_android_example.presentation.product.handler

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.common.ViewHandler
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import com.leinaro.mercadolibre_android_example.presentation.product.ProductFragment
import com.leinaro.mercadolibre_android_example.presentation.product.ProductRecyclerViewAdapter
import com.leinaro.mercadolibre_android_example.presentation.product.ProductViewData
import com.leinaro.mercadolibre_android_example.presentation.product.ShowProductList

object ShowProductListViewHandler :
    ViewHandler<ShowProductList, BaseViewModel<ProductViewData>> {
    override fun ShowProductList.perform(context: Any, viewModel: BaseViewModel<ProductViewData>) {
        when (context) {
            is ProductFragment -> showProductList(context, products)
        }
    }

    private fun showProductList(productFragment: ProductFragment, products: List<Product>) {
        productFragment.binding.progressBar.visibility = View.GONE

        with(productFragment.binding.list) {
            layoutManager = LinearLayoutManager(productFragment.requireContext())
            adapter = ProductRecyclerViewAdapter(products)
            visibility = View.VISIBLE
        }
    }

}
