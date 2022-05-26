package com.zillennium.utswap.models

import com.google.gson.annotations.SerializedName

class ProjectInfoDetailModel (
    @SerializedName("titleInfo")
    var titleInfo: String,
    @SerializedName("descriptionInfo")
    var descriptionInfo: String
)