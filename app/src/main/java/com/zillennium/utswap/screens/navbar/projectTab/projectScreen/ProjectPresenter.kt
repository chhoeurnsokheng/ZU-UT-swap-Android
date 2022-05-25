package com.zillennium.utswap.screens.navbar.projectTab.projectScreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.google.gson.JsonObject
import com.zillennium.utswap.Datas.APIs.APIModel
import com.zillennium.utswap.Datas.APIs.APIRepository
import com.zillennium.utswap.Datas.APIs.APIService
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.TestModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectPresenter : BaseMvpPresenterImpl<ProjectView.View>(),
    ProjectView.Presenter {


    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getDataFromApi() {


        APIRepository.getPost().enqueue(object: Callback<APIModel.MarketModel>{
            override fun onResponse(
                call: Call<APIModel.MarketModel>,
                response: Response<APIModel.MarketModel>
            ) {
                val myItem: APIModel.MarketModel? = response.body()

                Log.d("123123123123", myItem?.status.toString())
                Log.d("123123123123", myItem?.message.toString())
                myItem?.datas?.market?.map {
                    Log.d("123123123123", it.name.toString())
                }
            }

            override fun onFailure(call: Call<APIModel.MarketModel>, t: Throwable) {
                Log.d("123123123123", t.toString())
            }
        })

//        APIRepository.hasGetPost().enqueue(object: Callback<JsonObject>{
//            override fun onResponse(
//                call: Call<JsonObject>,
//                response: Response<JsonObject>
//            ) {
//                println(response);
////                Log.d("123123123123", response.body().toString())
////                val myItem: List<JsonObject>? = response.body()
////                myItem?.map {
////                    Log.d("123123123123", it.title)
////                }
//
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                Log.d("123123123123", t.toString())
//            }
//        })
    }

//    fun getDataFromApi(){
//        APIService.create().getPost().subscribe { response ->
////            Log.d("123123123123", response.toString())
////            mView?.onGetPhoto(response.count())
//        }
//    }
}