package com.zillennium.utswap.Datas.APIs

import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface APIService {
    @GET(APIConstant.photos)
    fun getPost() : Call<JsonObject>

    @GET(APIConstant.photos)
    fun hasGetPost() : Observable<JsonObject>

    @Headers("Content-Type: application/json")
    @POST(APIConstant.issues)
    fun storeIssues(): Observable<JsonObject>

}