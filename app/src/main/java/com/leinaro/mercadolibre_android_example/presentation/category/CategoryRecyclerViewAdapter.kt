package com.leinaro.mercadolibre_android_example.presentation.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.inSpans
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leinaro.mercadolibre_android_example.R
import com.leinaro.mercadolibre_android_example.presentation.common.PressableSpan
import com.leinaro.mercadolibre_android_example.presentation.commons.setPressableLinkSpannedString
import com.leinaro.mercadolibre_android_example.presentation.model.Category
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import com.leinaro.mercadolibre_android_example.presentation.product.ProductRecyclerViewAdapter


interface OnCategoryClickListener {
    fun onShowMoreClick(categoryId: String)
}

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
        with(holder.productList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ProductRecyclerViewAdapter(item.products)
        }
        holder.showMore.setPressableLinkSpannedString {
            inSpans(PressableSpan() {
                listener?.onShowMoreClick(item.id)
            }) {
                append("Ver más")
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.category_name)
        val productList: RecyclerView = view.findViewById(R.id.product_list)
        val showMore: TextView = view.findViewById(R.id.show_more)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}