package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListExchangeOrdersBinding
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorInt

class OrderAdapter(private var listener: OnClickDelete): BaseRecyclerViewAdapterGeneric<TradingList.TradeOrderPendingEntrust, OrderAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(root: ItemListExchangeOrdersBinding): BaseViewHolder<ItemListExchangeOrdersBinding>(root)
    {
        fun bindData(orders: TradingList.TradeOrderPendingEntrust, position: Int){
            binding.apply {
                txtUt.text = groupingSeparatorInt(orders.num.toString().toInt())
                txtPrice.text = "$ ${orders.price.toString().toDouble().let { groupingSeparator(it) }}"
                txtDate.text = orders.addtime.toString()

//        if(orders.txtStatus == "Limit / Sell")
//        {
//            holder.txtStatus.text = orders.txtStatus
//            holder.txtStatus.setTextColor(Color.RED)
//        }
//        else{
//            holder.txtStatus.text = orders.txtStatus
//        }

                if(orders.tradetype.toString() == "Limit")
                {
                    if(orders.type.toString() == "1")
                    {
                       txtStatus.text = "Limit / Buy"
                    }else{
                        txtStatus.text = "Limit / Sell"
                        txtStatus.setTextColor(Color.RED)
                    }
                }else{
                    txtStatus.text = "Market"
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

                btnDelete.setOnClickListener {
                    listener.clickMe(orders)
                }
            }
        }
    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    )= ItemViewHolder(ItemListExchangeOrdersBinding.inflate(inflater,parent,false))

    override fun onBindItemHolder(holder: ItemViewHolder, position: Int, context: Context) {
        holder.bindData(items[position],position)
    }

    interface OnClickDelete{
        fun clickMe(tradeOrder: TradingList.TradeOrderPendingEntrust)
    }
}