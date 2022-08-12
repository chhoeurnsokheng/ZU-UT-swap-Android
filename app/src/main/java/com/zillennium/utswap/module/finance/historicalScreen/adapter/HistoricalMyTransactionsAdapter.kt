package com.zillennium.utswap.module.finance.historicalScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListFinanceHistoricalMyTransactionsBinding
import com.zillennium.utswap.models.financeHistorical.Historical
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.groupingSeparator

class HistoricalMyTransactionsAdapter(var item:List<Historical.DataTransaction>): RecyclerView.Adapter<HistoricalMyTransactionsAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(root: ItemListFinanceHistoricalMyTransactionsBinding) : BaseViewHolder<ItemListFinanceHistoricalMyTransactionsBinding>(root){
        fun bidData(myTransaction: Historical.DataTransaction){
            binding.apply {
                titleTransaction.text = myTransaction.remark
                dateTransaction.text = myTransaction.addtimeReable
                if(myTransaction.type == "buy"){
                    if(myTransaction.name.toString() == "issue"){
                        imageBalance.setImageResource(Constants.HistoricalMyTransactionIcon.SubScription)
                    }else{
                        imageBalance.setImageResource(Constants.HistoricalMyTransactionIcon.Buy)
                    }

                    amountBalance.text = "$" + groupingSeparator(myTransaction.mum_a!!)
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
                }else if (myTransaction.type == "sell"){
                    imageBalance.setImageResource(Constants.HistoricalMyTransactionIcon.Sell)
                    amountBalance.text = "$" + groupingSeparator(myTransaction.mum_a!!)
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(ItemListFinanceHistoricalMyTransactionsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       holder.bidData(item[position])
    }

    override fun getItemCount(): Int {
        return  item.size
    }
}