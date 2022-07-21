package com.zillennium.utswap.Datas.APIs

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.zillennium.utswap.Datas.StoredPreferences.SystemPreferences
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object APIInstance {

  private var hosting: String = SystemPreferences().API_HOSTING.toString()
  private var firebase: String = SystemPreferences().API_FIREBASE.toString()
  private var debug: String = SystemPreferences().API_DEBUG.toString()
  private var webSocket: String = "wss://dex.binance.org/"
  private var timeout: Int = SystemPreferences().API_TIMEOUT?.toInt() ?: 30

  private val headersInterceptor by lazy {
    Interceptor { chain ->
      val requestBuilder = chain.request().newBuilder()
      chain.proceed(requestBuilder.build())
    }
  }

  private val okHttpClient by lazy {
    OkHttpClient.Builder()
      .followRedirects(true)
      .addInterceptor(headersInterceptor)
      .connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
      .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
      .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
      .build()
  }

  private fun retrofitBuild(url: String): Retrofit {
    return Retrofit.Builder()
      .baseUrl(url)
      .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
      .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
      .client(okHttpClient)
      .build()
  }
  
  val testAPI: APIService = retrofitBuild("https://jsonplaceholder.typicode.com/").create(APIService::class.java)

  val webSocketAPI: APIService by lazy { retrofitBuild(webSocket).create(APIService::class.java) }

  val hostingAPI: APIService by lazy { retrofitBuild(debug).create(APIService::class.java) }



}