package com.zillennium.utswap.Datas.APIs

import com.google.gson.JsonObject
import com.zillennium.utswap.models.TestModel
import retrofit2.Call
import rx.Observable

object APIRepository {
    fun getPost(): Call<APIModel.MarketModel> {
        return APIInstance.hostingAPI.getPost()
    }

    fun hasGetPost(): Call<JsonObject> {
        return APIInstance.hostingAPI.hasGetPost()
    }

}