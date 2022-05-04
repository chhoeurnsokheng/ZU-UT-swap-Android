package com.zillennium.utswap.screens.wallet.transferScreen

import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityWalletTransactionBinding
import java.io.IOException

class TransferActivity :
    BaseMvpActivity<TransferView.View, TransferView.Presenter, ActivityWalletTransactionBinding>(),
    TransferView.View {

    override var mPresenter: TransferView.Presenter = TransferPresenter()
    override val layoutResource: Int = R.layout.activity_wallet_transaction

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

            }
            // Code
        } catch (error: IOException) {
            // Must be safe
        }
    }
}