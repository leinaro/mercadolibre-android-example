package com.leinaro.mercadolibre_android_example.presentation.common

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.leinaro.mercadolibre_android_example.R

object ShowGeneralErrorViewHandler :
    ViewHandler<ErrorViewData, BaseViewModel<ErrorViewData>> {
    override fun ErrorViewData.perform(context: Any, viewModel: BaseViewModel<ErrorViewData>) {
        when (context) {
            is AppCompatActivity -> showDefaultErrorMessage(context)
            is Fragment -> showDefaultErrorMessage(context.requireContext())
        }

    }

    private fun showDefaultErrorMessage(context: Context) {
        MaterialAlertDialogBuilder(context,
            R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
            .setMessage(context.resources.getString(R.string.default_error_message))
            .setPositiveButton(context.resources.getString(R.string.ok)) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
}