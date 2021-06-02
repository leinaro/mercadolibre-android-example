package com.leinaro.mercadolibre_android_example.presentation.productdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.leinaro.mercadolibre_android_example.R
import java.util.*


class PicturesAdapter(val context: Context, val pictures: List<String>) : PagerAdapter() {
    override fun getCount(): Int {
        return pictures.size
    }

    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = inflater.inflate(R.layout.image_item, container, false)
        val imageView: ImageView = itemView.findViewById<View>(R.id.imageView) as ImageView
        Glide.with(context).load(pictures[position]).into(imageView)

        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}
