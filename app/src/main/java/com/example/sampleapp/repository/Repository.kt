package com.example.sampleapp.repository

import com.example.sampleapp.model.Model
import com.example.sampleapp.network.ApiController
import com.example.sampleapp.network.NoConnectionException
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor (private val apiController: ApiController) {

    suspend fun getImageData(): MutableList<Model.ImageData> =
        processResponse(apiController.getImageData())

    private fun processResponse(
        response: Response<MutableList<Model.ImageData>>
    ): MutableList<Model.ImageData> {

        val responseData = response.body()

        if (responseData == null)
            throw NoConnectionException("No Data Available")

        return responseData

    }

}