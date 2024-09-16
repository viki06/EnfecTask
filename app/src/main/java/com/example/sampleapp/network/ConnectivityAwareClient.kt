package com.example.sampleapp.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request

class ConnectivityAwareClient(
    private var client: OkHttpClient,
    private var cm: ConnectivityManager
) : OkHttpClient() {

    override fun newCall(request: Request): Call {

        val network = cm.activeNetwork

        if (network == null ||
            !(cm.getNetworkCapabilities(network)?.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR)!! ||
                    cm.getNetworkCapabilities(network)?.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI)!!)) {

            throw NoConnectionException("No network connection")

        }

        return client.newCall(request)

    }

}