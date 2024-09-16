package com.example.sampleapp.network

import com.example.sampleapp.model.Model
import retrofit2.Response
import retrofit2.http.GET

interface ApiController {

    @GET("photos")
    suspend fun getImageData() : Response<MutableList<Model.ImageData>>


}