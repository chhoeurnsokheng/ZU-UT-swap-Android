package com.zillennium.utswap.Datas.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.P)
class UI(
    context: Context?,
    name: String?,
    version: Int,
    openParams: SQLiteDatabase.OpenParams
) : SQLiteOpenHelper(context, name, version, openParams) {

    public var TABLE_NAME = "ui_db"
    public var UI_ID = "ui_id"
    public var UI_NAME = "ui_name"
    public var UI_EVENT = "ui_event"
    public var UI_DETAIL = "ui_detail"
    public var UI_DATE = "ui_date"

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}