package com.example.sampleapp.network

import android.net.ConnectivityManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val TIMEOUT_DURATION = 120 * 1000L

    private lateinit var retrofit: Retrofit

    fun getRetrofit(connectivityManager : ConnectivityManager): Retrofit {

        if (!this::retrofit.isInitialized) {

            retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client((ConnectivityAwareClient(OkHttpClient.Builder().apply {
                    readTimeout(TIMEOUT_DURATION, TimeUnit.MILLISECONDS)
                    connectTimeout(TIMEOUT_DURATION, TimeUnit.MILLISECONDS)
                    writeTimeout(TIMEOUT_DURATION, TimeUnit.MILLISECONDS)
                }.build(), connectivityManager)))
                .build()

        }

        return retrofit


    }

}