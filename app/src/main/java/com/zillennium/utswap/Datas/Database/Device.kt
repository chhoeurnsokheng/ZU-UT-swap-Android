package com.zillennium.utswap.Datas.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class Device(
    context: Context?,
    name: String?,
    version: Int,
    openParams: SQLiteDatabase.OpenParams
) : SQLiteOpenHelper(context, name, version, openParams) {

    public var TABLE_NAME = "device_db"
    public var DEVICE_ID = "device_id"
    public var DEVICE_OS = "device_os"
    public var DEVICE_NAME = "device_name"
    public var DEVICE_NUMBER = "device_number"
    public var DEVICE_VERSION = "device_version"
    public var DEVICE_IMEI = "device_imei"

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}