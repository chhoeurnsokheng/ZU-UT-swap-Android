package com.zillennium.utswap.api.manager

import android.content.Context
import com.zillennium.utswap.api.Header
import com.zillennium.utswap.models.newsService.News
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiNewsImp: ApiManager() {
    fun getNews(context: Context,p: Int): Observable<News.NewsRes> =
        mNewsService.news(
            Header.getHeader(Header.Companion.AuthType.REQUIRED, context),
            p
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getNewsDetail(id: String): Observable<News.NewsDetailRes> =
        mNewsService.newsDetail(
            id
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
