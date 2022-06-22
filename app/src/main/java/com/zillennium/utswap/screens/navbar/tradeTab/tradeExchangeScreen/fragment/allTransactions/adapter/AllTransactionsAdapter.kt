package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.allTransactions.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.allTransactions.AllTransactions

class AllTransactionsAdapter (
    arrayList: ArrayList<AllTransactions>,
) :
    RecyclerView.Adapter<AllTransactionsAdapter.ViewHolder>() {
    private var arrayList: ArrayList<AllTransactions> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_exchange_all_transactions, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        internal val txtTime: TextView = itemView.findViewById(R.id.txt_time)
        internal val txtVolume: TextView = itemView.findViewById(R.id.txt_volume)
        internal val txtPrice: TextView = itemView.findViewById(R.id.txt_price)
        internal val txtGross: TextView = itemView.findViewById(R.id.txt_gross)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val allTransactions: AllTransactions = arrayList[position]

        holder.txtDate.text = allTransactions.txtDate
        holder.txtGross.text = allTransactions.txtGross
        holder.txtTime.text = allTransactions.txtTime
        holder.txtVolume.text = allTransactions.txtVolume
        holder.txtPrice.text = allTransactions.txtPrice

    }

    init {
        this.arrayList = arrayList
    }
}