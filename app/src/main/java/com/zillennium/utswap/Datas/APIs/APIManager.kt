package com.zillennium.utswap.Datas.APIs

import com.zillennium.utswap.models.TestModel
import retrofit2.Call
import retrofit2.http.GET


interface APIManager {
    @GET("photos")
    fun getPost() : List<TestModel>
}