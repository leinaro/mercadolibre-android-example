package com.leinaro.mercadolibre_android_example.presentation.search

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.common.ViewHandler
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import com.leinaro.mercadolibre_android_example.presentation.product.OnProductClickListener
import com.leinaro.mercadolibre_android_example.presentation.product.ProductRecyclerViewAdapter

object ShowProductsViewHandler : ViewHandler<ShowProductSearch, BaseViewModel<SearchViewData>> {
    override fun ShowProductSearch.perform(
        context: Any,
        viewModel: BaseViewModel<SearchViewData>,
    ) {
        when (context) {
            is SearchFragment -> {
                showProductList(context, products)
                showLoading(context, isLoading)
            }
        }
    }

    private fun showProductList(searchFragment: SearchFragment, products: List<Product>) {
        val productListener = object : OnProductClickListener {
            override fun onProductItemClick(productId: String) {
                val navController = searchFragment.findNavController()
                val action =
                    SearchFragmentDirections.actionSearchFragmentToProductDetailsFragment(
                        productId
                    )
                navController.navigate(action)
            }
        }

        with(searchFragment.binding.list) {
            layoutManager = LinearLayoutManager(searchFragment.requireContext())
            adapter = ProductRecyclerViewAdapter(products, true, productListener)
            visibility = View.VISIBLE
        }
    }

    private fun showLoading(
        searchFragment: SearchFragment,
        isLoading: Boolean,
    ) {
        searchFragment.binding.progressBar.visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
