package com.leinaro.mercadolibre_android_example.presentation.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leinaro.mercadolibre_android_example.R
import com.leinaro.mercadolibre_android_example.dummy.DummyContent.DummyItem
import com.leinaro.mercadolibre_android_example.presentation.model.Product

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 */
class ProductRecyclerViewAdapter(
    private val values: List<Product>,
    private val large: Boolean = true,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = if (large) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_product_large, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_product, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = values[position]
        if (holder is ViewHolder) {
            holder.name.text = item.name
            holder.price.text = item.price
            holder.thumbnail.contentDescription = item.name
            Glide.with(holder.thumbnail.context).load(item.image).into(holder.thumbnail)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.product_name)
        val price: TextView = view.findViewById(R.id.product_price)
        val thumbnail: ImageView = view.findViewById(R.id.product_thumbnail)

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'"
        }
    }
}