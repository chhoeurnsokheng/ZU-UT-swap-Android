package com.zillennium.utswap.models.province

import android.view.textclassifier.ConversationActions
import java.io.Serializable

/**
 * Created by Sokheng Chhoeurn on 2/8/22.
 * Build in Mac
 */

object PProvinceObj{

    class ProvinceRes {
        var status: Int? = null
        var message: String? = null
        var data: ArrayList<Items>? = arrayListOf()
    }
    class DistrictRes {
        var status: Int? = null
        var message: String? = null
        var data: ArrayList<Items>? = arrayListOf()
    }
    class CommuneRes {
        var status: Int? = null
        var message: String? = null
        var data: ArrayList<Items>? = arrayListOf()
    }
    class Items {
        var code: Int? = null
        var english: String? = null
    }
    class BodyProvince(
        var parent_code: String?
    )



}





