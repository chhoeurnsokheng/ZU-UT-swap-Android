package com.zillennium.utswap.api.service

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.withdraw.WithdrawObj
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import rx.Observable

/**
 * Created by Sokheng Chhoeurn on 31/8/22.
 * Build in Mac
 */
interface WithDrawService {
    @GET(ApiSettings.PATH_LIST_AVAILABLE_WITHDRAWAL_BANK)
    fun getListAvailableWithdrawBank(
        @HeaderMap header: Map<String, String>
    ):Observable<WithdrawObj.WithdrawRes>
}