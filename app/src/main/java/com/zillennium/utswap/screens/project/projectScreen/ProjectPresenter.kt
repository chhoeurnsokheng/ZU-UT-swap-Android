package com.zillennium.utswap.screens.project.projectScreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.zillennium.utswap.Datas.APIs.APIRepository
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl


class ProjectPresenter : BaseMvpPresenterImpl<ProjectView.View>(),
    ProjectView.Presenter {

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun getDataFromApi() {

//        CheckNetworkConnection(UTSwapApp.instance, object : CheckNetworkConnection(){})

//        val disposables = CompositeDisposable()
//        disposables.add(
            APIRepository.hasGetPost()
                .subscribe({ JsonObject ->

//                    val model = JsonObject.asJsonObject.get("data").asJsonObject.get("market").asJsonArray
//
//                    val test : ProjectModel = Gson().fromJson(model, ProjectModel::class.java)
//
//                    APIModel.Market.


                    Log.d("hello world", JsonObject.toString())
                }, { error ->
                    println(error.localizedMessage)
                    Log.d("hello world", error.localizedMessage.toString())
                })



//        )
//        disposables.clear()





//        println(APIRepository.hasGetPost())
//
//        return APIRepository.hasGetPost()

//        return APIRepository.hasGetPost()

//        APIRepository.getPost().enqueue(object: Callback<JsonObject>{
//            override fun onResponse(
//                call: Call<JsonObject>,
//                response: Response<JsonObject>
//            ) {
//                val myItem: JsonObject? = response.body()
//
//                myItem?.get("data")?.asJsonObject?.get("market")?.asJsonArray?.map {
//                    println(it.asJsonObject.get("name"))
//                }
//
//
//
//
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                Log.d("123123123123", t.toString())
//            }
//        })

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
