package com.leinaro.mercadolibre_android_example.presentation.product.handler

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.common.ViewHandler
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import com.leinaro.mercadolibre_android_example.presentation.product.*
import com.leinaro.mercadolibre_android_example.presentation.product.adapter.OnProductClickListener
import com.leinaro.mercadolibre_android_example.presentation.product.adapter.ProductRecyclerViewAdapter

object ShowProductListViewHandler :
    ViewHandler<ShowProductList, BaseViewModel<ProductViewData>> {
    override fun ShowProductList.perform(context: Any, viewModel: BaseViewModel<ProductViewData>) {
        when (context) {
            is ProductFragment -> showProductList(context, products)
        }
    }

    private fun showProductList(productFragment: ProductFragment, products: List<Product>) {
        productFragment.binding.progressBar.visibility = View.GONE

        val productListener = object : OnProductClickListener {
            override fun onProductItemClick(productId: String) {
                val navController = productFragment.findNavController()
                val action =
                    ProductFragmentDirections.actionProductFragmentToProductDetailsFragment(
                        productId
                    )
                navController.navigate(action)
            }
        }

        with(productFragment.binding.list) {
            layoutManager = LinearLayoutManager(productFragment.requireContext())
            adapter = ProductRecyclerViewAdapter(products, true, productListener)
            visibility = View.VISIBLE
        }
    }

}
