package com.zillennium.utswap.screens.navbar.projectTab.projectScreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.TestModel
import retrofit2.Call
import rx.Observable

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