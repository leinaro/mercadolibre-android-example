package com.leinaro.mercadolibre_android_example.presentation.common

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.core.text.buildSpannedString
import androidx.core.text.getSpans
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.leinaro.mercadolibre_android_example.R


fun <T> Fragment.setObserver(viewModel: BaseViewModel<T>) {
    viewModel.getViewData().observe(this, Observer {
        handleViewData(it, viewModel)
    })
    viewModel.getErrorViewData().observe(this, Observer {
        handleViewData(it, viewModel)
    })
}

@Suppress("UNCHECKED_CAST")
fun <T, C> Fragment.handleViewData(viewDataState: ViewDataState<T>, viewModel: C) {
    (viewDataState.second as ViewHandler<T, C>).run {
        viewDataState.first.perform(
            context = this@handleViewData,
            viewModel = viewModel
        )
    }
}

inline fun TextView.setPressableLinkSpannedString(builderAction: SpannableStringBuilder.() -> Unit) {
    highlightColor = Color.TRANSPARENT
    movementMethod = LinkMovementMethod()//PressableLinkMovementMethod

    val spannedString = buildSpannedString(builderAction)
    text = spannedString

    spannedString.getSpans<PressableSpan>().takeIf {
        it.isNotEmpty()
    }?.map {
        it.onClickListener
    }?.let {
        setTag(R.id.view_onclick_event, it)
    }
}