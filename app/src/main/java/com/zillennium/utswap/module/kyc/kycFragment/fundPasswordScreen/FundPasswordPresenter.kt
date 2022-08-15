package com.zillennium.utswap.module.kyc.kycFragment.fundPasswordScreen

import android.content.Context
import android.os.Bundle
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.utils.Constants.KeyViewPdf.Companion.content
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import rx.Subscription
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class FundPasswordPresenter : BaseMvpPresenterImpl<FundPasswordView.View>(),
    FundPasswordView.Presenter {
    var addKYCSubscription: Subscription? = null

    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }

    override fun addKyc(param: User.Kyc, context: Context) {
       addKYCSubscription?.unsubscribe()
        addKYCSubscription = ApiUserImp().addKyc(param,context).subscribe({
            mView?.addKycSuccess(it)
        }, { error ->
            object : CallbackWrapper(error, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(
                    status: ApiManager.NetworkErrorStatus,
                    data: Any
                ) {
                    mView?.addKycFail(data.toString())
                }
            }
        })
    }

//    private  fun submitRequestBody(mObject: User.Kyc): RequestBody {
//        val requestBody = MultipartBody.Builder()
//        requestBody.setType(MultipartBody.FORM)
//        mObject.truename.apply {
//            this.let { requestBody.addFormDataPart("truename", it) }
//        }
//        mObject.gender.apply {
//            requestBody.addFormDataPart("gender", this)
//        }
//        mObject.occupation.apply {
//            requestBody.addFormDataPart("occupation", this)
//        }
//        mObject.companyname.apply {
//            requestBody.addFormDataPart("companyname", this)
//        }
//        mObject.email.apply {
//            requestBody.addFormDataPart("email", this)
//        }
//        mObject.citycode.apply {
//            requestBody.addFormDataPart("citycode", this)
//        }
//        mObject.districtcode.apply {
//            requestBody.addFormDataPart("districtcode", this)
//        }
//        mObject.communecode.apply {
//            requestBody.addFormDataPart("communecode", this)
//        }
//        mObject.streetnumber.apply {
//            requestBody.addFormDataPart("streetnumber", this)
//        }
//        mObject.idcardinfo.apply {
//            requestBody.addFormDataPart("idcardinfo", this)
//        }
//        mObject.idcardfront.apply {
//            if (isNotEmpty()) {
//                val bodyBye = readByteArrayFromFile(this)
//                    .toRequestBody(
//                        "multipart/form-data".toMediaTypeOrNull(),
//                        0, content.size
//                    )
//                requestBody.addFormDataPart("idcardfront", this, bodyBye)
//            }
//        }
//        mObject.idcardrear.apply {
//            if (isNotEmpty()) {
//                val bodyBye = readByteArrayFromFile(this)
//                    .toRequestBody(
//                        "multipart/form-data".toMediaTypeOrNull(),
//                        0, content.size
//                    )
//                requestBody.addFormDataPart("idcardrear", this, bodyBye)
//            }
//        }
//        mObject.userImage.apply {
//            if (isNotEmpty()) {
//                val bodyBye = readByteArrayFromFile(this)
//                    .toRequestBody(
//                        "multipart/form-data".toMediaTypeOrNull(),
//                        0, content.size
//                    )
//                requestBody.addFormDataPart("userImage", this, bodyBye)
//            }
//        }
//        mObject.termandcondition.apply {
//            requestBody.addFormDataPart("termandcondition ", this.toString())
//        }
//        mObject.paypassword.apply {
//            requestBody.addFormDataPart("paypassword", this)
//        }
//        mObject.repaypassword.apply {
//            requestBody.addFormDataPart("repaypassword", this)
//        }
//
//        return requestBody.build()
//    }


    private  fun readByteArrayFromFile(filePath: String): ByteArray {
        var fileInputStream: FileInputStream? = null
        var bytesArray: ByteArray = byteArrayOf()
        try {
            var file = File(filePath)
 //         file = Compressor(UTSwapApp.instance).compressToFile(file)
       //     file = Compressor.compress(UTSwapApp.instance, file)
            bytesArray = ByteArray(file.length().toInt())
            fileInputStream = FileInputStream(file)
            fileInputStream.read(bytesArray)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return bytesArray
    }


}