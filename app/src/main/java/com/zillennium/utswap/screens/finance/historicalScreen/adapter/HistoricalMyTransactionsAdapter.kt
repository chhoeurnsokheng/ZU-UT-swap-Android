package com.zillennium.utswap.screens.finance.historicalScreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.financeHistorical.HistoricalMyTransactionsModel
import com.zillennium.utswap.utils.groupingSeparator

class HistoricalMyTransactionsAdapter (arrayList: ArrayList<HistoricalMyTransactionsModel>)
    : RecyclerView.Adapter<HistoricalMyTransactionsAdapter.ViewHolder>() {

    private var listData: ArrayList<HistoricalMyTransactionsModel> = arrayList

    inner class ViewHolder (view: View): RecyclerView.ViewHolder(view){
        var imageImageView: ImageView = view.findViewById<View>(R.id.image_balance) as ImageView
        var titleTransaction: TextView = view.findViewById<View>(R.id.title_transaction) as TextView
        var dateTransaction: TextView = view.findViewById<View>(R.id.date_transaction) as TextView
        var amountBalance: TextView = view.findViewById<View>(R.id.amount_balance) as TextView
        var layoutRecentNewsCard: LinearLayout = view.findViewById<View>(R.id.lay_my_trans) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_finance_historical_my_transactions, viewGroup, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val financeHistoricalList: HistoricalMyTransactionsModel = listData[position]
        holder.imageImageView.setImageResource(financeHistoricalList.imageMyTrans)
        holder.titleTransaction.text = financeHistoricalList.titleMyTrans
        holder.dateTransaction.text = financeHistoricalList.dateMyTrans
        if(financeHistoricalList.amountMyTrans < 0){
            val length = financeHistoricalList.amountMyTrans.toString().length
            if ((financeHistoricalList.amountMyTrans.toString()[0] == '-')) {
                if (length > 1) {
                    holder.amountBalance.text = "-$" + groupingSeparator(financeHistoricalList.amountMyTrans).substring(1, length)
                }
            }
            holder.amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
        }else {
            holder.amountBalance.text = "$" + groupingSeparator(financeHistoricalList.amountMyTrans)
            holder.amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
        }
    }

    override fun getItemCount(): Int {
        val limit = 15
        return Math.min(listData.size, limit)
    }
}