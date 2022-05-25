package com.zillennium.utswap.screens.navbar.tradeTab.fragment.Transactions.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentTransactionsDetailBinding

class TransactionDetailFragment :
    BaseMvpFragment<TransactionDetailView.View, TransactionDetailView.Presenter, FragmentTransactionsDetailBinding>(),
    TransactionDetailView.View {

    override var mPresenter: TransactionDetailView.Presenter = TransactionDetailPresenter()
    override val layoutResource: Int = R.layout.fragment_transactions_detail
    private var volume: String? = ""
    private var price: String? = ""

    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnBack.setOnClickListener{
                    findNavController().popBackStack()
                }

                txtVolume.text = arguments?.getString("ut").toString()
                txtDate.text = arguments?.getString("date").toString()
                txtStatus.text = arguments?.getString("status")
                if(txtStatus.text.toString() == "SELL")
                {
                    txtStatus.setTextColor(resources.getColor(R.color.main_red))
                }

                txtPrice.text = arguments?.getString("price")

                volume = arguments?.getString("volume")
                price = arguments?.getString("price")

                txtGross.text = (volume?.toFloat()?.times(price!!.toFloat())).toString()
                txtNet.text = (txtGross.text.toString().toFloat() + 0.30).toString()

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
}