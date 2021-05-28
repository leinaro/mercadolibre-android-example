package com.leinaro.mercadolibre_android_example.presentation.category.handler

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leinaro.mercadolibre_android_example.presentation.category.*
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
            is CategoryFragment -> showCategoryList(context, categories)
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
            }
        }

        categoryFragment.binding.progressBar.visibility = View.GONE
        with(categoryFragment.binding.list) {
            layoutManager = LinearLayoutManager(categoryFragment.requireContext())
            adapter = CategoryRecyclerViewAdapter(categories, listener)
            visibility = View.VISIBLE
        }
    }
}
