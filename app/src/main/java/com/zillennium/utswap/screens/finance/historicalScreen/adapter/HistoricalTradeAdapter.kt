package com.zillennium.utswap.screens.finance.historicalScreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.financeHistorical.HistoricalTradeModel
import com.zillennium.utswap.utils.groupingSeparator
import org.w3c.dom.Text

class HistoricalTradeAdapter(arrayList: ArrayList<HistoricalTradeModel>) :
    RecyclerView.Adapter<HistoricalTradeAdapter.ViewHolder>() {

    private val listData: ArrayList<HistoricalTradeModel> = arrayList

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitleTrade: TextView = view.findViewById<View>(R.id.txt_title_trade) as TextView
        var txtUTAmountTrade: TextView = view.findViewById<View>(R.id.txt_ut_amount_trade) as TextView
        var txtOpenAmountTrade: TextView = view.findViewById<View>(R.id.txt_open_amount) as TextView
        var txtCloseAmountTrade: TextView = view.findViewById<View>(R.id.txt_close_amount) as TextView
        var txtHighAmountTrade: TextView = view.findViewById<View>(R.id.txt_high_amount) as TextView
        var txtLowAmountTrade: TextView = view.findViewById<View>(R.id.txt_low_amount) as TextView
        var layTrade: LinearLayout = view.findViewById<View>(R.id.lay_historical_trade) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_finance_historical_trade, viewGroup, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tradeArrayList: HistoricalTradeModel = listData[position]
        holder.txtTitleTrade.text = tradeArrayList.titleTrade
        holder.txtUTAmountTrade.text = groupingSeparator(tradeArrayList.utAmount) + " UT"
        holder.txtOpenAmountTrade.text = "$" + groupingSeparator(tradeArrayList.openAmountTrade)
        holder.txtCloseAmountTrade.text = "$" + groupingSeparator(tradeArrayList.closeAmountTrade)
        holder.txtHighAmountTrade.text = "$" + groupingSeparator(tradeArrayList.highAmountTrade)
        holder.txtLowAmountTrade.text = "$" + groupingSeparator(tradeArrayList.lowAmountTrade)

    }

    override fun getItemCount(): Int {
        val limit = 15
        return Math.min(listData.size, limit)
    }
}