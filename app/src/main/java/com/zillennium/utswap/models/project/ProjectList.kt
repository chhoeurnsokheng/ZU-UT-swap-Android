package com.zillennium.utswap.models.project

object ProjectList {
    class ProjectListRes{
        var status : Int? =null
        var message : String? = null
        var data : List<ProjectListData>? = null
    }

    class ProjectListData{
        var id : String? = null
        var project_name : String? = null
        var base_price : Float? = null
        var target_price : Float? = null
        var total_ut : Int? = null
        var strategy : String? = null
        var action : String? = null
        var image : String? = null
    }

    class ProjectListObject(
        var name: String? = null,
        var page: Int? = null,
        var sort: String? = null
    )
}