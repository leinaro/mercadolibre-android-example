package com.leinaro.mercadolibre_android_example.presentation.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leinaro.mercadolibre_android_example.R
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import com.leinaro.mercadolibre_android_example.presentation.product.adapter.OnProductClickListener
import com.leinaro.mercadolibre_android_example.presentation.product.adapter.ProductRecyclerViewAdapter


/**
 * [RecyclerView.Adapter] that can display a [Category] and its [Product].
 */
class CategoryRecyclerViewAdapter(
    private val values: List<Category>,
    private val listener: OnCategoryClickListener? = null,
) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.name
        holder.contentView.setOnClickListener {
            listener?.onShowMoreClick(item.id)
        }
        val productListener = object : OnProductClickListener {
            override fun onProductItemClick(productId: String) {
                listener?.onProductItemClick(productId)
            }
        }

        with(holder.productList) {
            adapter =
                ProductRecyclerViewAdapter(item.products.subList(0, 6), false, productListener)
            visibility = View.VISIBLE
        }
        holder.showMore.setOnClickListener {
            listener?.onShowMoreClick(item.id)
        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.category_name)
        val productList: RecyclerView = view.findViewById(R.id.product_list)
        val showMore: Button = view.findViewById(R.id.show_more)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}