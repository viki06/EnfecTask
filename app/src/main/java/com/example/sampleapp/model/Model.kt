package com.example.sampleapp.model

object Model {

    data class ImageData(
         val albumId : Int = -1,
        val id : Int = -1,
        val title : String = "",
        val url : String = "",
        val thumbnailUrl : String = ""
    )

}
