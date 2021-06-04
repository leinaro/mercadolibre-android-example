package com.leinaro.mercadolibre_android_example.presentation.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leinaro.mercadolibre_android_example.R
import com.leinaro.mercadolibre_android_example.presentation.model.Product
import java.text.NumberFormat
import java.util.*

interface OnProductClickListener {
    fun onProductItemClick(productId: String)
}

/**
 * [RecyclerView.Adapter] that can display a [Product].
 */
class ProductRecyclerViewAdapter(
    private val values: List<Product>,
    private val large: Boolean = true,
    private val listener: OnProductClickListener? = null,
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
            val format: NumberFormat = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("COP")
            holder.price.text = format.format(item.price?.toDouble())
            holder.thumbnail.contentDescription = item.name
            Glide.with(holder.thumbnail.context).load(item.image).into(holder.thumbnail)
            holder.itemView.setOnClickListener {
                listener?.onProductItemClick(item.id)
            }
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