package com.zillennium.utswap.models.newsService

object News {
    class NewsRes{
        var status: Int? = null
        var message: String? = null
        var data: NewsData? = null
    }

    class NewsData{
        var TOTALPAGE: Int? = null
        var NEW: List<NewsNew>? = null
    }

    class NewsNew{
        var id: String? = null
        var title: String? = null
        var addtime: String? = null
        var img: String? = null
    }

    /** News Detail */
    class NewsDetailRes{
        var status: Int? = null
        var message: String? = null
        var data: NewsDetailData? = null
    }

    class NewsDetailData{
        var id: String? = null
        var date: NewsDateDetail? = null
        var source: String? = null
        var title: String? = null
        var content: String? = null
        var image: String? = null
    }
    class NewsDateDetail{
        var addtime: String? = null
        var endtime: String? = null
    }
}