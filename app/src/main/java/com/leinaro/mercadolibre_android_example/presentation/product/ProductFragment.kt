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
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Products.
 */
@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels()

    private val args: ProductFragmentArgs by navArgs()

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

        this.setObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.getProductsByCategory(args.categoryId)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}