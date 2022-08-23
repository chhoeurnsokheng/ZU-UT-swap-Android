package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.newsService.News
import retrofit2.http.*
import rx.Observable

interface NewsService {
    @POST(ApiSettings.PATH_NEWS)
    fun news(
        @HeaderMap headers: Map<String, String>,
        @Body body: News.NewsObj
    ): Observable<News.NewsRes>

    @FormUrlEncoded
    @POST(ApiSettings.PATH_NEWS_DETAIL)
    fun newsDetail(
        @Field("id") id: String
    ): Observable<News.NewsDetailRes>

    @POST(ApiSettings.PATH_NEWS_HOME)
    fun newsHome(
        @HeaderMap headers: Map<String, String>
    ):Observable<News.NewsRes>
}