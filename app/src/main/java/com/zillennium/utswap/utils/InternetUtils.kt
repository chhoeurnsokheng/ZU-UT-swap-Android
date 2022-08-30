package com.zillennium.utswap.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build

/**
 * Created by Sokheng Chhoeurn on 20/8/22.
 * Build in Mac
 */

object InternetUtils {
    class ConnectivityMonitor private constructor(private val connectivityManager: ConnectivityManager) {
        companion object {
            var isConnected = false
            var onConnectionCallBack: ((status: Boolean) -> Unit)? = null
        }

        constructor(application: Application) : this(
            connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

        ) {
            init()
        }

        private val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isConnected = true
                onConnectionCallBack?.invoke(true)
            }

            override fun onLost(network: Network) {
                isConnected = false
                onConnectionCallBack?.invoke(false)
            }
        }

        private fun init() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val networkAvailability =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                networkAvailability?.let {
                    if (
                        networkAvailability.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        networkAvailability.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    ) onConnectionCallBack?.invoke(true) else onConnectionCallBack?.invoke(false)
                } ?: run {
                    onConnectionCallBack?.invoke(false)
                }
                connectivityManager.registerDefaultNetworkCallback(
                    networkCallback
                )
            } else {
                val builder = NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
                connectivityManager.registerNetworkCallback(builder, networkCallback)
            }
        }
    }
}