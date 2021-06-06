package com.leinaro.mercadolibre_android_example.presentation.common

import android.content.Context
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.leinaro.mercadolibre_android_example.R
import com.leinaro.mercadolibre_android_example.datasource.remote.NoConnectivityException

object ShowGeneralErrorViewHandler :
    ViewHandler<ErrorViewData, BaseViewModel<ErrorViewData>> {
    override fun ErrorViewData.perform(context: Any, viewModel: BaseViewModel<ErrorViewData>) {
        Log.e("Error", error.message ?: "")
        error.printStackTrace()
        when (error) {
            is NoConnectivityException -> {
                when (context) {
                    is AppCompatActivity -> showNetworkConnection(context)
                    is Fragment -> showNetworkConnection(context)
                }
            }
            else -> {
                when (context) {
                    is AppCompatActivity -> showDefaultErrorMessage(context)
                    is Fragment -> showDefaultErrorMessage(context.requireContext())
                }
            }
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

    private fun showNetworkConnection(context: AppCompatActivity) {
        val contextView = context.findViewById<View>(R.id.main_container)
        Snackbar.make(contextView, R.string.working_with_no_network, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.ok) {}
            .show()
    }

    private fun showNetworkConnection(context: Fragment) {
        val contextView = context.requireActivity().findViewById<View>(R.id.main_container)
        Snackbar.make(contextView, R.string.working_with_no_network, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.ok) {}
            .show()
    }

}