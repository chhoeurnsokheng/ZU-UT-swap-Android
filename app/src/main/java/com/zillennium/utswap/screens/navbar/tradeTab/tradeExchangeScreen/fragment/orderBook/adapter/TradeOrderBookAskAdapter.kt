package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.orderBook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.orderBookList.OrderBookList
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorInt

class TradeOrderBookAskAdapter  (
    arrayList: ArrayList<OrderBookList>,
) :
    RecyclerView.Adapter<TradeOrderBookAskAdapter.ViewHolder>() {
    private var arrayList: ArrayList<OrderBookList> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_exchange_order_book_ask, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val txtVolume: TextView = itemView.findViewById(R.id.txt_volume_ask)
        internal val txtBid: TextView = itemView.findViewById(R.id.txt_ask)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderBookList: OrderBookList = arrayList[position]

        holder.txtBid.text =  groupingSeparator(orderBookList.txtBid.toDouble())
        holder.txtVolume.text = groupingSeparatorInt(orderBookList.txtVolume.toInt())
    }

    init {
        this.arrayList = arrayList
    }
}