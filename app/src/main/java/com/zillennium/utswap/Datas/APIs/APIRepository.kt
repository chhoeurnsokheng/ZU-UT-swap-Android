package com.zillennium.utswap.Datas.APIs

import com.google.gson.JsonObject
import com.zillennium.utswap.models.TestModel
import retrofit2.Call
import rx.Observable

object APIRepository {
    fun getPost(): Call<List<TestModel>> {
        return APIInstance.testAPI.getPost()
    }

    fun hasGetPost(): Call<JsonObject> {
        return APIInstance.testAPI.hasGetPost()
    }

}