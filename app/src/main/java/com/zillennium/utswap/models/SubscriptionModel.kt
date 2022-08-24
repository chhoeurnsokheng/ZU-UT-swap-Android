package com.zillennium.utswap.models

import java.io.Serializable

class SubscriptionModel(
    var tv_title: String,
    var tv_dollar: Int,
    var tv_day_lock: Int,
    var tv_ut_value: Int,
    var tv_ut_main_value: Int

):
    Serializable
