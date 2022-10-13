package com.zillennium.utswap.module.finance.balanceScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListFinanceBalanceBinding
import com.zillennium.utswap.models.financeBalance.BalanceFinance
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.UtilKt

class FinanceBalanceAdapter(onClickAdapter: OnClickAdapter): BaseRecyclerViewAdapterGeneric<BalanceFinance.GetBalanceSearchDateTransactionData, FinanceBalanceAdapter.ItemViewHolder>(){

    private val onClickAdapter: OnClickAdapter

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListFinanceBalanceBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: FinanceBalanceAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListFinanceBalanceBinding) : BaseViewHolder<ItemListFinanceBalanceBinding>(root) {
        fun bidData(userBalanceTransaction: BalanceFinance.GetBalanceSearchDateTransactionData) {
            binding.apply {
                dateTransaction.text =  userBalanceTransaction.addtimeReadable
                titleTransaction.text = userBalanceTransaction.remark
                if (userBalanceTransaction.type == "1"){
                    amountBalance.text = "$ " + userBalanceTransaction.total?.let { UtilKt().formatValue(it, "###,###.##") }
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                    imageBalance.setImageResource(Constants.UserBalanceIcon.BalanceTradeSell)
                }else if (userBalanceTransaction.type == "2"){
                    amountBalance.text = "-$ " + userBalanceTransaction.total?.let { UtilKt().formatValue(it, "###,###.##") }
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    imageBalance.setImageResource(Constants.UserBalanceIcon.BalanceTradeBuy)
                }else if (userBalanceTransaction.type == "3"){
                    amountBalance.text = "$ " + userBalanceTransaction.total?.let { UtilKt().formatValue(it, "###,###.##") }
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                    imageBalance.setImageResource(Constants.UserBalanceIcon.BalanceDeposit)
                }else if (userBalanceTransaction.type == "4"){
                    amountBalance.text = "-$ " + userBalanceTransaction.total?.let { UtilKt().formatValue(it, "###,###.##") }
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    imageBalance.setImageResource(Constants.UserBalanceIcon.BalanceWithdrawal)
                }else if (userBalanceTransaction.type == "5"){
                    amountBalance.text = "-$ " + userBalanceTransaction.total?.let { UtilKt().formatValue(it, "###,###.##") }
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    imageBalance.setImageResource(Constants.UserBalanceIcon.BalanceSubscriptions)
                }else if (userBalanceTransaction.type == "RECEIVE"){
                    amountBalance.text = "$ " + userBalanceTransaction.total?.let { UtilKt().formatValue(it, "###,###.##") }
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                    imageBalance.setImageResource(Constants.UserBalanceIcon.BalanceTransfer)
                }else if (userBalanceTransaction.type == "SEND"){
                    amountBalance.text = "-$ " + userBalanceTransaction.total?.let { UtilKt().formatValue(it, "###,###.##") }
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    imageBalance.setImageResource(Constants.UserBalanceIcon.BalanceTransfer)
                }

                linearBalance.setOnClickListener {
                    onClickAdapter.onClickMe(userBalanceTransaction)
                }
            }
        }
    }

    interface OnClickAdapter{
        fun onClickMe(financeUserBalanceTransaction: BalanceFinance.GetBalanceSearchDateTransactionData)
    }

    init {
        this.onClickAdapter = onClickAdapter
    }
}
