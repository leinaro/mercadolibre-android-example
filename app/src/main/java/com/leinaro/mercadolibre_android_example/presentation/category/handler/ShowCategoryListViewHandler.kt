package com.leinaro.mercadolibre_android_example.presentation.category.handler

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.leinaro.mercadolibre_android_example.presentation.category.*
import com.leinaro.mercadolibre_android_example.presentation.category.adapter.CategoryRecyclerViewAdapter
import com.leinaro.mercadolibre_android_example.presentation.category.adapter.OnCategoryClickListener
import com.leinaro.mercadolibre_android_example.presentation.common.BaseViewModel
import com.leinaro.mercadolibre_android_example.presentation.common.ViewHandler
import com.leinaro.mercadolibre_android_example.presentation.model.Category

object ShowCategoryListViewHandler :
    ViewHandler<ShowCategoryList, BaseViewModel<CategoryViewData>> {
    override fun ShowCategoryList.perform(
        context: Any,
        viewModel: BaseViewModel<CategoryViewData>,
    ) {
        when (context) {
            is CategoryFragment -> {
                showCategoryList(context, categories)
                showLoading(context, isLoading)
            }
        }
    }

    private fun showLoading(
        categoryFragment: CategoryFragment,
        isLoading: Boolean,
    ) {
        categoryFragment.binding.progressBar.visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun showCategoryList(
        categoryFragment: CategoryFragment,
        categories: List<Category>,
    ) {
        val listener by lazy {
            object : OnCategoryClickListener {
                override fun onShowMoreClick(categoryId: String) {
                    val navController = categoryFragment.findNavController()
                    val action = CategoryFragmentDirections.actionCategoryFragmentToProductFragment(
                        categoryId
                    )
                    navController.navigate(action)
                }

                override fun onProductItemClick(productId: String) {
                    val navController = categoryFragment.findNavController()
                    val action =
                        CategoryFragmentDirections.actionCategoryFragmentToProductDetailsFragment(
                            productId
                        )
                    navController.navigate(action)
                }
            }
        }

        with(categoryFragment.binding.list) {
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(categoryFragment.requireContext())
            adapter = CategoryRecyclerViewAdapter(categories, listener)
            visibility = View.VISIBLE
        }
    }
}
