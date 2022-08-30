package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.deposite.DataQueryOrderObj
import com.zillennium.utswap.models.deposite.DepositObj
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Sokheng Chhoeurn on 16/8/22.
 * Build in Mac
 */
interface DespositeService {
    @GET(ApiSettings.PATH_LIST_PAYMENT_METHOD)
    fun getLIstPaymentMethod(
        @HeaderMap headerMap: Map<String ,String>
    ):Observable<DepositObj.DepositRes>
    @POST(ApiSettings.PATH_ONLINE_DEPOSIT)
    fun depositMoney(
        @HeaderMap headerMap: Map<String ,String>,
        @Body body: DepositObj.DepositRequestBody
    ):Observable<DepositObj.DepositReturn>
   @POST(ApiSettings.PATH_FINANCE_TRANSFER_LOGS)
   fun getFinanceTransferLog(
       @HeaderMap headerMap: Map<String ,String>
   ):Observable<DepositObj.DepositRes>

   @POST(ApiSettings.PATH_QUERY_ORDER)
   fun getQueryOrder(
       @HeaderMap headerMap: Map<String ,String>,
       @Body body: DepositObj.DataQueryOrderBody
   ):Observable<DataQueryOrderObj.DataQueryOrderRes>


}