package com.zillennium.utswap

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class UTSwapApp : MultiDexApplication() {

    companion object {
        lateinit var instance: UTSwapApp
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}