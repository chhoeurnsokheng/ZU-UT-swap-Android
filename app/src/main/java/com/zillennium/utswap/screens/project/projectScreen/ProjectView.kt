package com.zillennium.utswap.screens.project.projectScreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.TestModel

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