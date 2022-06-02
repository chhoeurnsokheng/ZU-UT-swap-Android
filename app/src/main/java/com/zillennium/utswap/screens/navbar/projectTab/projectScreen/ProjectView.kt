package com.zillennium.utswap.screens.navbar.projectTab.projectScreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.TestModel
import retrofit2.Call

class ProjectView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetPhoto(data: List<TestModel>){
            Log.d("test on get photo", "test on get photo")
        }
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getDataFromApi()
    }
}