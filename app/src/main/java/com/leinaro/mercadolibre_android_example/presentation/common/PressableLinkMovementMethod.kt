package com.leinaro.mercadolibre_android_example.presentation.common

import android.graphics.Rect
import android.text.Selection
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.view.MotionEvent
import android.widget.TextView

object PressableLinkMovementMethod : LinkMovementMethod() {

    private var lastPressedSpan: PressableSpan? = null

    override fun onTouchEvent(
        widget: TextView, buffer: Spannable, event: MotionEvent,
    ): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val thisSpan = getPressedSpan(widget, buffer, event)
                when {
                    thisSpan == null -> clearLastPressedSpan(buffer)
                    lastPressedSpan == thisSpan -> Unit
                    lastPressedSpan == null -> {
                        thisSpan.setPressed(true)
                        lastPressedSpan = thisSpan
                        Selection.setSelection(
                            buffer,
                            buffer.getSpanStart(thisSpan),
                            buffer.getSpanEnd(thisSpan)
                        )
                    }
                }
                true
            }
            MotionEvent.ACTION_UP -> {
                if (lastPressedSpan == null && widget.isInside(event)) {
                    super.onTouchEvent(widget, buffer, event)
                } else {
                    lastPressedSpan?.onClick(widget)
                    clearLastPressedSpan(buffer)
                    true
                }
            }
            else -> {
                clearLastPressedSpan(buffer)
                super.onTouchEvent(widget, buffer, event)
            }
        }
    }

    private fun clearLastPressedSpan(buffer: Spannable) {
        lastPressedSpan?.setPressed(false)
        lastPressedSpan = null
        Selection.removeSelection(buffer)
    }

    private fun getPressedSpan(
        widget: TextView, buffer: Spannable, event: MotionEvent,
    ): PressableSpan? {
        if (!widget.isInside(event)) return null

        val x = event.x - widget.totalPaddingLeft + widget.scrollX
        val y = event.y - widget.totalPaddingTop + widget.scrollY

        val offset = with(widget.layout) {
            val line = getLineForVertical(y.toInt())
            getOffsetForHorizontal(line, x)
        }

        val links = buffer.getSpans(offset, offset, PressableSpan::class.java)
        return links.firstOrNull()
    }

    private val targetViewRect = Rect()

    private fun TextView.isInside(event: MotionEvent): Boolean {
        targetViewRect.set(0, 0, width, height)
        return targetViewRect.contains(event.x.toInt(), event.y.toInt())
    }


}

