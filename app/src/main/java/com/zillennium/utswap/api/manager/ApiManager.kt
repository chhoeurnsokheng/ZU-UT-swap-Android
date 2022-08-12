package com.zillennium.utswap.api.manager


/**
 * @author chhoeurnsokheng
 * Created 5/7/22 at 3:14 PM
 * By Mac
 */

import android.annotation.SuppressLint
import android.content.Context
import com.zillennium.utswap.BuildConfig
import com.zillennium.utswap.api.service.*
import com.zillennium.utswap.utils.LoggerUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.atomic.AtomicInteger
import javax.net.ssl.*

open class ApiManager {

    private val mServerUrl = getBaseServerUrl()

    protected lateinit var mUserService: UserService
    protected lateinit var mNewsService: NewsService


    protected lateinit var mHistorical: HistoricalService

    protected lateinit var mAccountLogsService: AccountLogsService
    protected lateinit var mCustomerSupport: CustomerSupportService
    protected lateinit var mProjectService: ProjectService



    protected lateinit var mHomeService: HomeService

    protected lateinit var mFinanceService: FinanceService

    protected lateinit var mProvince: ProvincesService


    protected lateinit var mContext: Context

    
    companion object {
        var mRetryCounter: AtomicInteger = AtomicInteger(0)
    }

    init {
        initServices(initRetrofit())
    }

    open fun getBaseServerUrl(): String {

        return  BuildConfig.BASE_URL

    }

    //INIT ALL PROFIT OBJECT
    private fun initServices(retrofit: Retrofit) {
        mUserService = retrofit.create(UserService::class.java)
        mNewsService = retrofit.create(NewsService::class.java)
        mAccountLogsService = retrofit.create(AccountLogsService::class.java)
        mCustomerSupport = retrofit.create(CustomerSupportService::class.java)
        mProjectService = retrofit.create(ProjectService::class.java)

        mHistorical = retrofit.create(HistoricalService::class.java)


        mHomeService = retrofit.create(HomeService::class.java)

        mFinanceService = retrofit.create(FinanceService::class.java)

        mProvince = retrofit.create(ProvincesService::class.java)


    }

    private fun initRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                LoggerUtil.debug("RAW_BODY", "$message")
            }
        })
        logging.level =  HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().apply {
            try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )

                // Install the all-trusting trust manager
                val sslContext: SSLContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
                this.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                this.hostnameVerifier(HostnameVerifier { _, _ -> true })
            } catch (e: Exception) {
                e.printStackTrace()
            }
            networkInterceptors().add(Interceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            })
            if (BuildConfig.DEBUG) {
                addInterceptor(logging)
                //addInterceptor(interceptor)
            }
        }



        return Retrofit.Builder().baseUrl(mServerUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //.addConverterFactory(createMoshiConverter())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
    }

    /**
     * Implement Observable for retry to handle error 401 only.
     *
     * @param o Throwable when error by pass to Observable to resolve error.
     * @return Observable type object.
     */


    private fun createMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create()

    enum class NetworkErrorStatus {
        UnProcessableEntity,
        ON_UNKNOW_ERROR,
        ON_ERROR,
        UNAUTHORIZED,
        ON_TIMEOUT,
        ON_NETWORK_ERROR,
        NOT_FOUND
    }
}
