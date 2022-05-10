package com.zillennium.utswap.Datas.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class Bug(context: Context?, name: String?, version: Int, openParams: SQLiteDatabase.OpenParams) :
    SQLiteOpenHelper(context, name, version, openParams) {

    public var TABLE_NAME = "bug_db"
    public var BUG_ID = "bug_id"
    public var DEVICE_ID = "device_id"
    public var BUG_NAME = "bug_name"
    public var BUG_DETAIL = "bug_detail"
    public var BUG_DATE = "bug_date"

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}