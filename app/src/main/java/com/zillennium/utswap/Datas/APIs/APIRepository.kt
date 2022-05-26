package com.zillennium.utswap.Datas.APIs

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import io.reactivex.rxjava3.disposables.CompositeDisposable

object APIRepository {
    fun getPost(): Call<JsonObject> {
        return APIInstance.hostingAPI.getPost()
    }

    fun hasGetPost(): Observable<JsonObject> {
        return APIInstance.hostingAPI.hasGetPost()
    }

}