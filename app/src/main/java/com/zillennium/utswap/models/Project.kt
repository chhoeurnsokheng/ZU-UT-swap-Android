package com.zillennium.utswap.models

import java.io.Serializable


class Project(
    var id: Int,
    var publicDate: String,
    var imageIcon: Int,
    var titleProject: String,
    var subTitle: String,
    var status: String,
    ) :
    Serializable