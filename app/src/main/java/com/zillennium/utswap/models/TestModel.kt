package com.zillennium.utswap.models

import java.io.Serializable


class TestModel(
    var albumId: Int,
    var id: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String,
    ) :
    Serializable