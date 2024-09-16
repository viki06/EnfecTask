package com.example.sampleapp.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.sampleapp.network.ApiController
import com.example.sampleapp.network.RetrofitClient
import com.example.sampleapp.repository.Repository
import com.example.sampleapp.ui.ImageRecyclerAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiController(application: Application): ApiController =
        RetrofitClient.getRetrofit(application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .create(ApiController::class.java)

    @Provides
    @Singleton
    fun provideRepository(apiController: ApiController) = Repository(apiController = apiController)

    @Provides
    fun provideImageAdapter() = ImageRecyclerAdapter()

}