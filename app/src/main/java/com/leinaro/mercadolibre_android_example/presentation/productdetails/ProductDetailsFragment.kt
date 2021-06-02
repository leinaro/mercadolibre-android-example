package com.leinaro.mercadolibre_android_example.presentation.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.leinaro.mercadolibre_android_example.databinding.ProductDetailsFragmentBinding
import com.leinaro.mercadolibre_android_example.presentation.common.setObserver
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a [Product].
 */
@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: ProductDetailsFragmentBinding? = null
    val binding get() = _binding!!

    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ProductDetailsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        //viewModel.getProduct("productId")
        return view
    }


}