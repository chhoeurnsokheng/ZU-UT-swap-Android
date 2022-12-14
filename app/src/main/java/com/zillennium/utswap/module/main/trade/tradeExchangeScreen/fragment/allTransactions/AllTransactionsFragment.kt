package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.allTransactions

import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.ListDatas.allTransactions.AllTransactionsData
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeAllTransactionsBinding
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.allTransactions.adapter.AllTransactionsAdapter


class AllTransactionsFragment :
    BaseMvpFragment<AllTransactionsView.View, AllTransactionsView.Presenter, FragmentExchangeAllTransactionsBinding>(),
    AllTransactionsView.View {

    override var mPresenter: AllTransactionsView.Presenter = AllTransactionsPresenter()
    override val layoutResource: Int = R.layout.fragment_exchange_all_transactions


    override fun initView() {
        super.initView()
        try {
            binding.apply {
//                if(SessionPreferences().SESSION_STATUS  == true && SessionPreferences().SESSION_KYC  == true){
//                    txtMessage.visibility = View.GONE
//                    linearAllTransHistory.visibility = View.VISIBLE
//                }

                val linearLayoutManager = LinearLayoutManager(requireContext())
                rvAllTrans.layoutManager = linearLayoutManager
                val allTransactionsAdapter = AllTransactionsAdapter(
                    AllTransactionsData.LIST_OF_ALL_TRANSACTIONS()
                )
                rvAllTrans.adapter = allTransactionsAdapter
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
}