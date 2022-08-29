package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.financeTransfer.Transfer
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import rx.Observable

interface TransferService{

    @POST (ApiSettings.PATH_FINANCE_TRANSFER)
    fun getFinanceTransfer(
        @HeaderMap headers: Map<String, String>,
        @Body body: Transfer.GetTransferObject
    ): Observable<Transfer.GetTransfer>

    @POST (ApiSettings.PATH_FINANCE_VALIDATE_TRANSFER)
    fun getFinanceValidateTransfer(
        @HeaderMap header: Map<String, String>,
        @Body body: Transfer.GetValidateTransferObject
    ): Observable<Transfer.GetValidateTransfer>
}