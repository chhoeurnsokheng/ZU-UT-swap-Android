package com.zillennium.utswap.Datas.APIs


import com.google.gson.JsonObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call

object APIRepository {
    fun getPost(): Call<JsonObject> {
        return APIInstance.hostingAPI.getPost()
    }

    fun hasGetPost(): Observable<JsonObject> {
        return APIInstance.testAPI.hasGetPost()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())

    }

}