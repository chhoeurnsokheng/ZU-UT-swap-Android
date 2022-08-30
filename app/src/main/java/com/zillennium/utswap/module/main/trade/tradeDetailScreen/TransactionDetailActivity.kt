package com.zillennium.utswap.module.main.trade.tradeDetailScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityTradeTransactionsDetailBinding
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.dialog.MarketDialog
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.dialog.SuccessPlaceOrderDialog
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorInt
import kotlin.concurrent.fixedRateTimer


class TransactionDetailActivity :
    BaseMvpActivity<TransactionDetailView.View, TransactionDetailView.Presenter, ActivityTradeTransactionsDetailBinding>(),
    TransactionDetailView.View {

    override var mPresenter: TransactionDetailView.Presenter = TransactionDetailPresenter()
    override val layoutResource: Int = R.layout.activity_trade_transactions_detail
    private var id: String? = null

    companion object {
        fun launchTransactionDetailsActivity(context: Context, transactionId: String?) {
            val intent = Intent(context, TransactionDetailActivity::class.java)
            intent.putExtra("id", transactionId)
            context.startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        if (intent.hasExtra("id")) {
            id = intent?.getStringExtra("id")
        }

        binding.swipeRefresh.setColorSchemeColors(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))

        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                mPresenter.onGetTransactionDetail(TradingList.TradeTransactionDetailObj(id.toString().toInt()),UTSwapApp.instance)
            }
        }

        onSwipeRefresh()

        binding.apply {
            imgBack.setOnClickListener{
                finish()
            }
        }
    }

    override fun onGetTransactionDetailSuccess(data: TradingList.TradeTransactionDetailData) {
        binding.apply {
            txtDate.text = data.addtime.toString()
            txtTransactionId.text = data.transaction_id.toString()
            txtPrice.text = groupingSeparator(data.price.toString().toDouble())
            txtVolume.text = data.volume?.let { groupingSeparatorInt(it.toInt()) }
            txtGross.text = data.gross.toString()
            txtFee.text = data.fee.toString()
            txtNet.text = groupingSeparator(data.net.toString().toDouble())
            txtName.text = data.name.toString()

            txtUnitUt.visibility = View.VISIBLE
            txtUnitFee.visibility = View.VISIBLE
            txtUnitGross.visibility = View.VISIBLE
            txtUnitNet.visibility = View.VISIBLE
            txtUnitPrice.visibility = View.VISIBLE

            if(data.type == "1")
            {
                txtStatus.text = resources.getString(R.string.buy)
            }else{
                txtStatus.text = resources.getString(R.string.sell)
                txtStatus.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
            }

            progressBar.visibility = View.GONE
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onGetTransactionDetailFail(data: TradingList.TradeTransactionDetailData) {
        binding.progressBar.visibility = View.VISIBLE
        binding.swipeRefresh.isRefreshing = false
    }

    private fun onSwipeRefresh(){
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                swipeRefresh.isRefreshing = true
                Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
                    if (isConnected) {
                        mPresenter.onGetTransactionDetail(TradingList.TradeTransactionDetailObj(id.toString().toInt()),UTSwapApp.instance)
                    }
                }
            }
        }
    }


//    private fun toolBar() {
//        setSupportActionBar(binding.includeLayout.tb)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//        binding.includeLayout.apply {
//          //  tbTitle.setText(R.string.account)
//            tb.setNavigationOnClickListener {
//                finish()
//            }
//        }
//    }
}