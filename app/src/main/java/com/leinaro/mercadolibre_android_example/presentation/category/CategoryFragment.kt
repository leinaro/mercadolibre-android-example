package com.leinaro.mercadolibre_android_example.presentation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.leinaro.mercadolibre_android_example.databinding.FragmentCategoryListBinding
import com.leinaro.mercadolibre_android_example.presentation.common.setObserver
import dagger.hilt.android.AndroidEntryPoint
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import com.leinaro.mercadolibre_android_example.presentation.model.Category

/**
 * A fragment representing a list of [Category] with [Product].
 */
@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryListBinding? = null
    val binding get() = _binding!!

    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.getCategories()
        return view
    }
}