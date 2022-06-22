package com.zillennium.utswap.screens.finance.historicalScreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.financeHistorical.HistoricalAllTransactionsModel
import com.zillennium.utswap.utils.groupingSeparator

class HistoricalAllTransactionsAdapter(arrayList: ArrayList<HistoricalAllTransactionsModel>) :
    RecyclerView.Adapter<HistoricalAllTransactionsAdapter.ViewHolder>() {

    private val listData: ArrayList<HistoricalAllTransactionsModel> = arrayList

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var dateAllTrans: TextView = view.findViewById<View>(R.id.txt_date_all_trans) as TextView
        var volumeAllTrans: TextView = view.findViewById<View>(R.id.txt_volume_all_trans) as TextView
        var priceAllTrans: TextView = view.findViewById<View>(R.id.txt_price_all_trans) as TextView
        var grossAllTrans: TextView = view.findViewById<View>(R.id.txt_gross_all_trans) as TextView
        var layAllTrans: LinearLayout = view.findViewById(R.id.lay_all_trans) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_finance_historical_all_transactions, viewGroup, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val arrayAllTans: HistoricalAllTransactionsModel = listData[position]
        holder.dateAllTrans.text = arrayAllTans.dateAllTrans
        holder.volumeAllTrans.text = arrayAllTans.volumeAllTrans.toString()
        holder.priceAllTrans.text = "$" + groupingSeparator(arrayAllTans.priceAllTrans)
        holder.grossAllTrans.text = "$" + groupingSeparator(arrayAllTans.grossAllTrans)
    }

    override fun getItemCount(): Int {
        val limit = 15
        return Math.min(listData.size, limit)
    }


}