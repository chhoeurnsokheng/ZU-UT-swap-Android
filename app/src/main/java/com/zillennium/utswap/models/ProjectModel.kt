package com.zillennium.utswap.models

import java.io.Serializable


class ProjectModel(
    var id: Int,
    var publicDate: String,
    var image: String,
    var titleProject: String,
    var subTitle: String,
    var status: String,
    ) :
    Serializable