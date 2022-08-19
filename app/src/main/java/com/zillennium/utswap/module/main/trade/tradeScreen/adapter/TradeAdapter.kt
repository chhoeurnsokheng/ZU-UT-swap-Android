package com.zillennium.utswap.module.main.trade.tradeScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListPortfolioTradeBinding
import com.zillennium.utswap.models.TradeModel
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorInt

class TradeAdapter(private var listener: Listener): BaseRecyclerViewAdapterGeneric<TradeModel, TradeAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(root: ItemListPortfolioTradeBinding): BaseViewHolder<ItemListPortfolioTradeBinding>(root)
    {
        fun bindData(tradeList: TradeModel,position: Int){
           binding.apply {
               txtProject.text = tradeList.project_name

               if(tradeList.change.toDouble() < 0){
                   txtChange.text = groupingSeparator(tradeList.change.toDouble()) + "%"
                   txtChange.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
               }else if(tradeList.change.toDouble() == 0.00)
               {
                   txtChange.text = groupingSeparator(tradeList.change.toDouble()) + "%"
                   txtChange.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_222222))
               }else {
                   txtChange.text = "+" + groupingSeparator(tradeList.change.toDouble()) + "%"
                   txtChange.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
               }

               txtLast.text = "$" + groupingSeparator(tradeList.last.toDouble())
               txtVolume.text = groupingSeparatorInt(tradeList.volume.toDouble())

               if (items.size == 1) {
                   viewLine.visibility = View.GONE
               } else {
                   when (position) {
                       items.size - 1 -> {
                           viewLine.visibility = View.GONE
                       }
                       0 -> {
                           viewLine.visibility = View.VISIBLE
                       }
                       else -> {
                           viewLine.visibility = View.VISIBLE
                       }
                   }
               }

               linearLayout.setOnClickListener {
                   listener.clickMe(tradeList)
               }

               txtLast.setOnClickListener {
                   listener.clickMe(tradeList)
               }

               txtProject.setOnClickListener {
                   listener.clickMe(tradeList)
               }

               txtVolume.setOnClickListener {
                   listener.clickMe(tradeList)
               }

               txtChange.setOnClickListener {
                   listener.clickMe(tradeList)
               }
           }
        }
    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListPortfolioTradeBinding.inflate(inflater,parent,false))

    override fun onBindItemHolder(
        holder: TradeAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bindData(items[position],position)
    }

    interface Listener{
        fun clickMe(tradeProject:TradeModel)
    }

}