package com.zillennium.utswap.models.project

import io.reactivex.rxjava3.internal.util.EmptyComponent

object ProjectInfoDetail {

    class ProjectInfoDetailRes {
        var status: Int? = null
        var message: String? = null
        var data: ProjectInfoDetailData? = null
    }

    class ProjectInfoDetailData {
        var id: String? = null
        var project_name: String? = null
        var market_id: String? = null
        var market_name: String? = null
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
        var investment_information: InvestmentInformation? = null
        var documents: String? = null
        var images: Array<String> = emptyArray()
        var term_and_condition: String? = null
        var expected_return: Int? = null
    }
    class InvestmentInformation{
        var base:BaseObj ? = null
        var target:Targetobj? = null
    }
    class BaseObj{
        var base_ut_price:Double ? =  null
        var sqm:Double ? = null
        var value:Double ? =null
    }
    class Targetobj{
        var future_ut_price:Double? = null
        var sqm:Double? = null
        var value:Double? = null
    }
    class ProjectInfoDetailObject(
        var id: Int
    )
}