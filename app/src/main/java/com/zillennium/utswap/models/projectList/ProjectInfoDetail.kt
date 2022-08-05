package com.zillennium.utswap.models.projectList


object ProjectInfoDetail {
    class ProjectInfoDetailRes {
        var status: Int? = null
        var message: String? = null
        var data: ProjectInfoDetailData? = null
    }

    class ProjectInfoDetailData {
        var id: String? = null
        var project_name: String? = null
        var base_price: Float? = null
        var target_price: Float? = null
        var total_ut: Int? = null
        var action: String? = null
        var title_deed: String? = null
        var land_size: String? = null
        var indication_price: String? = null
        var future_price: String? = null
        var location: String? = null
        var google_map_link: String? = null
        var managed_by: String? = null
        var images: ArrayList<String>? = null
        var term_and_condition: String? = null
    }

    class ProjectInfoDetailObject(
        var body : Int
    )
}