package com.zillennium.utswap.Datas.APIs

import com.zillennium.utswap.Datas.StoredPreferences.SystemPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIInstance {

  private var hosting: String = SystemPreferences().API_HOSTING.toString()
  private var firebase: String = SystemPreferences().API_FIREBASE.toString()
  private var debug: String = SystemPreferences().API_DEBUG.toString()
  private var timeout: Int = SystemPreferences().API_TIMEOUT?.toInt() ?: 30

  private val okHttpClient by lazy {
    OkHttpClient.Builder()
      .connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
      .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
      .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
      .build()
  }

  private fun retrofitBuild(url: String): Retrofit {
    return Retrofit.Builder()
      .baseUrl(url)
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .client(okHttpClient)
      .build()
  }

  val testAPI: APIService = retrofitBuild("https://jsonplaceholder.typicode.com/").create(APIService::class.java)

  val hostingAPI: APIService by lazy { retrofitBuild(debug).create(APIService::class.java) }

  val firebaseAPI: APIService by lazy { retrofitBuild(firebase).create(APIService::class.java) }

}