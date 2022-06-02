package com.zillennium.utswap.screens.navbar.tradeTab.fragment.orderBook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.orderBookList.OrderBookList
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorInt

class TradeOrderBookBidAdapter (
    arrayList: ArrayList<OrderBookList>,
) :
    RecyclerView.Adapter<TradeOrderBookBidAdapter.ViewHolder>() {
    private var arrayList: ArrayList<OrderBookList> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_order_book_bid, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val txtVolume: TextView = itemView.findViewById(R.id.txt_volume)
        internal val txtBid: TextView = itemView.findViewById(R.id.txt_bid)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderBookList: OrderBookList = arrayList[position]

        holder.txtBid.text = groupingSeparator(orderBookList.txtBid.toDouble())
        holder.txtVolume.text = groupingSeparatorInt(orderBookList.txtVolume.toInt())
    }

    init {
        this.arrayList = arrayList
    }
}