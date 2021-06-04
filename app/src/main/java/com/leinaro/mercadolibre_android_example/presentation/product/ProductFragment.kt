package com.leinaro.mercadolibre_android_example.presentation.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.leinaro.mercadolibre_android_example.databinding.FragmentProductListBinding
import com.leinaro.mercadolibre_android_example.presentation.common.setObserver
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of [Product].
 */
@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels()

    private val args: ProductFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)

        viewModel.getProductsByCategory(args.categoryId)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}