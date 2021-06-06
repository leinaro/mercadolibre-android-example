package com.leinaro.mercadolibre_android_example.presentation.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.leinaro.mercadolibre_android_example.R
import com.leinaro.mercadolibre_android_example.databinding.FragmentSearchBinding
import com.leinaro.mercadolibre_android_example.presentation.MainActivity
import com.leinaro.mercadolibre_android_example.presentation.common.setObserver
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import dagger.hilt.android.AndroidEntryPoint


/**
 * A fragment representing a list of [Product] in a search.
 */
@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)
        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.getProducts("")
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.getProducts(query)

        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val activity = requireActivity() as MainActivity

        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().supportFragmentManager.popBackStack()
                activity.onBackPressed()
                true
            }
            R.id.action_search -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}