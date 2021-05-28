package com.leinaro.mercadolibre_android_example.presentation.common

import android.graphics.Color
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import java.lang.ref.WeakReference

class PressableSpan(
    private val textView: TextView,
    onClickListener: View.OnClickListener,
) : ClickableSpan() {

    private var isPressed = false
    private val textColor: Int = textView.linkTextColors.getColorForState(
        intArrayOf(android.R.attr.state_enabled), Color.BLACK
    )

    private val textColorPressed: Int = textView.linkTextColors.getColorForState(
        intArrayOf(android.R.attr.state_enabled, android.R.attr.state_pressed), Color.BLACK
    )

    val onClickListener: View.OnClickListener? get() = weakOnClickListener.get()
    private val weakOnClickListener = WeakReference(onClickListener)

    fun setPressed(isPressed: Boolean) {
        this.isPressed = isPressed
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.color = when (isPressed) {
            true -> textColorPressed
            false -> textColor
        }
    }

    override fun onClick(view: View) {
        weakOnClickListener.get()?.onClick(view)
    }
}