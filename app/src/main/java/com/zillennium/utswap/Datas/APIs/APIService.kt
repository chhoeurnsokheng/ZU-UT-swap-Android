package com.zillennium.utswap.Datas.APIs

import com.google.gson.JsonObject
import com.zillennium.utswap.models.TestModel
import retrofit2.Call
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable


interface APIService {
    @GET(APIConstant.photos)
    fun getPost() : Call<APIModel.MarketModel>

    @GET(APIConstant.photos)
    fun hasGetPost() : Call<JsonObject>

}