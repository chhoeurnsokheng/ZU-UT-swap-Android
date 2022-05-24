package com.zillennium.utswap.Datas.APIs

import com.zillennium.utswap.models.TestModel
import retrofit2.Call

class APIRepository {
    fun getPost(): List<TestModel> {
        return APIInstance.testAPI.getPost()
    }
}