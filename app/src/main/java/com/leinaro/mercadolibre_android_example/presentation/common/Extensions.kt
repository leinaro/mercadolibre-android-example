package com.leinaro.mercadolibre_android_example.presentation.common

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.leinaro.mercadolibre_android_example.R
import com.leinaro.mercadolibre_android_example.presentation.MainActivity


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

fun AppCompatActivity.addNoConnectionBarMessage(view: View) {
    val snackBar: Snackbar = Snackbar.make(
        view, R.string.working_with_no_network, Snackbar.LENGTH_INDEFINITE
    ).setAction(R.string.ok) {}

    val connectivityManager = getSystemService(ConnectivityManager::class.java)

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.e(MainActivity::class.simpleName, "The default network is now: " + network)
                snackBar.dismiss()
            }

            override fun onLost(network: Network) {
                Log.e(MainActivity::class.simpleName,
                    "The application no longer has a default network. The last default network was " + network)
                snackBar.show()
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) {
                Log.e(MainActivity::class.simpleName,
                    "The default network changed capabilities: " + networkCapabilities)
            }

            override fun onLinkPropertiesChanged(
                network: Network,
                linkProperties: LinkProperties,
            ) {
                Log.e(MainActivity::class.simpleName,
                    "The default network changed link properties: " + linkProperties)
            }
        })
    }
}