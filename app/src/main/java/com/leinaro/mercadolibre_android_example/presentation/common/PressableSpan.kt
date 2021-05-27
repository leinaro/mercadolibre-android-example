package com.leinaro.mercadolibre_android_example.presentation.common

import android.text.style.ClickableSpan
import android.view.View
import java.lang.ref.WeakReference

class PressableSpan(onClickListener: View.OnClickListener): ClickableSpan() {
    val onClickListener: View.OnClickListener? get() = weakOnClickListener.get()
    private val weakOnClickListener = WeakReference(onClickListener)

    override fun onClick(view: View) {
        weakOnClickListener.get()?.onClick(view)
    }
}