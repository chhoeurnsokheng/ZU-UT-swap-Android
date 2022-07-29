package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.newsService.News
import retrofit2.http.*
import rx.Observable

interface NewsService {
    @GET(ApiSettings.PATH_NEWS)
    fun news(
        @HeaderMap headers: Map<String, String>,
        @Query("p") p: Int
    ): Observable<News.NewsRes>

    @FormUrlEncoded
    @POST(ApiSettings.PATH_NEWS_DETAIL)
    fun newsDetail(
        @Field("id") id: String
    ): Observable<News.NewsDetailRes>
}