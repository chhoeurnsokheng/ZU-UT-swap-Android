package com.zillennium.utswap.screens.navbar.tradeTab.tradeDetailScreen

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityTradeTransactionsDetailBinding
import com.zillennium.utswap.utils.groupingSeparator


class TransactionDetailActivity :
    BaseMvpActivity<TransactionDetailView.View, TransactionDetailView.Presenter, ActivityTradeTransactionsDetailBinding>(),
    TransactionDetailView.View {

    override var mPresenter: TransactionDetailView.Presenter = TransactionDetailPresenter()
    override val layoutResource: Int = R.layout.activity_trade_transactions_detail
    private var volume: Int? = 0
    private var price: Double? = 0.0

    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnBack.setOnClickListener{
                    finish()
                }

                val arguments = intent.extras
                txtVolume.text = arguments?.getInt("ut").toString()
                txtDate.text = arguments?.getString("date").toString()
                txtPrice.text =  arguments?.getDouble("price").toString()

                txtStatus.text = arguments?.getString("status")
                if(txtStatus.text.toString() == "SELL")
                {
                    txtStatus.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
                }

                volume = arguments?.getInt("ut")
                price = arguments?.getDouble("price")

                txtGross.text = (volume?.times(price!!)?.let { groupingSeparator(it) }).toString()
                txtNet.text = (groupingSeparator(txtGross.text.toString().toFloat() + 0.30)).toString()

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
}