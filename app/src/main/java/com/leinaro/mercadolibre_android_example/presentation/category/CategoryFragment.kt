package com.leinaro.mercadolibre_android_example.presentation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.leinaro.mercadolibre_android_example.R
import com.leinaro.mercadolibre_android_example.databinding.FragmentCategoryListBinding
import com.leinaro.mercadolibre_android_example.presentation.common.setObserver
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import dagger.hilt.android.AndroidEntryPoint

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
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.title = getString(R.string.app_name)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        viewModel.getCategories()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}