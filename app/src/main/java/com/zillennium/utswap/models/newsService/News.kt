package com.zillennium.utswap.models.newsService

object News {
    class NewsRes{
        var status: Int? = null
        var message: String? = null
        var data: List<NewsData>? = null
    }

    class NewsData{
        var id: String? = null
        var title: String? = null
        var addtime: String? = null
        var content: String? = null
    }

    /** News Detail */
    class NewsDetailRes{
        var status: Int? = null
        var message: String? = null
        var data: NewsDetailData? = null
    }

    class NewsDetailData{
        var id: String? = null
        var date: String? = null
        var source: String? = null
        var title: String? = null
        var content: String? = null
        var image: String? = null
    }
}