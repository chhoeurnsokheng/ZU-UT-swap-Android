package com.zillennium.utswap.module.finance.transferScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.deposite.DepositObj
import com.zillennium.utswap.models.financeBalance.BalanceFinance
import com.zillennium.utswap.models.financeTransfer.Transfer
import com.zillennium.utswap.models.userService.User

class TransferView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetUserInfoSuccess(data: User.AppSideBarData)
        fun onGetUserInfoFail(data: User.AppSideBarData)
        fun onGetUserBalanceInfoSuccess(data: BalanceFinance.GetUserBalanceInfo)
        fun onGetUserBalanceInfoFail(data: BalanceFinance.GetUserBalanceInfo)
        fun onGetValidateTransferSuccess(data: Transfer.GetValidateTransferData)
        fun onGetValidateTransferFail(data: Transfer.GetValidateTransfer)
        fun onUserExpiredToken()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun onGetUserInfo(context: Context)
        fun onGetUserBalanceInfo(context: Context)
        fun onGetValidateTransfer(body: Transfer.GetValidateTransferObject, context: Context)
    }
}