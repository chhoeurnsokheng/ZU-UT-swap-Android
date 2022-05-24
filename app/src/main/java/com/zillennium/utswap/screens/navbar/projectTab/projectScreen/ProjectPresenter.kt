package com.zillennium.utswap.screens.navbar.projectTab.projectScreen

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.zillennium.utswap.Datas.APIs.APIRepository
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.TestModel
import retrofit2.Call

class ProjectPresenter : BaseMvpPresenterImpl<ProjectView.View>(),
    ProjectView.Presenter {

    val myResponse :MutableLiveData<TestModel> = MutableLiveData()

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getProject(): List<TestModel> {
        return APIRepository().getPost()
    }
}