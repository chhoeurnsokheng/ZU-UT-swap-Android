package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.Transactions.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListExchangeTransactionsBinding
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorInt

class TransactionAdapter(private var listener: OnClickTransactions): BaseRecyclerViewAdapterGeneric<TradingList.TradeMatchingTransactionEntrust,TransactionAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(root: ItemListExchangeTransactionsBinding): BaseViewHolder<ItemListExchangeTransactionsBinding>(root) {
        @SuppressLint("SetTextI18n")
        fun bindData(orders: TradingList.TradeMatchingTransactionEntrust, position: Int) {
            binding.apply {
                txtUt.text = groupingSeparatorInt(orders.num.toString().toInt())
                txtPrice.text = "$ ${orders.price.toString().toDouble().let { groupingSeparator(it) }}"
                txtDate.text = orders.addtime.toString()

                if(orders.type.toString() == "1"){
                    txtStatus.text = "Buy"
                }else{
                    txtStatus.text = "Sell"
                    txtStatus.setTextColor(Color.RED)
                }

                if (items.size == 1) {
                    line.visibility = View.GONE
                } else {
                    when (position) {
                        items.size - 1 -> {
                            line.visibility = View.GONE
                        }
                        0 -> {
                            line.visibility = View.VISIBLE
                        }
                        else -> {
                            //holder.itemView.setBackgroundResource(R.color.white)
                            line.visibility = View.VISIBLE
                        }
                    }
                }

                itemView.setOnClickListener {
                    listener.onClickMe(orders)
                }
            }
        }
    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    )= ItemViewHolder(ItemListExchangeTransactionsBinding.inflate(inflater,parent,false))

    override fun onBindItemHolder(holder: ItemViewHolder, position: Int, context: Context) {
        holder.bindData(items[position],position)
    }

    interface OnClickTransactions{
        fun onClickMe(orders: TradingList.TradeMatchingTransactionEntrust)
    }
}