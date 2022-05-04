package com.zillennium.utswap.screens.wallet.historicalScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.Historical.Historical

class HistoricalAdapter(
    arrayList: ArrayList<Historical>,
    onClickHistoricalHistory: OnClickHistoricalHistory
) :
    RecyclerView.Adapter<HistoricalAdapter.ViewHolder>() {
    private var arrayList = ArrayList<Historical>()
    private val onClickHistoricalHistory: OnClickHistoricalHistory
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_wallet_historical_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val historical = arrayList[position]
        holder.txtValue.text = historical.value
        holder.txtDate.text = historical.date
        if (arrayList.size == 1) {
            holder.line.visibility = View.GONE
        } else {
            if (arrayList.size - 1 == position) {
                holder.line.visibility = View.GONE
            } else if (0 == position) {
                holder.itemView.setBackgroundResource(R.drawable.card_view_whtie_border_top)
                holder.line.visibility = View.VISIBLE
            } else {
                holder.itemView.setBackgroundResource(R.color.white)
                holder.line.visibility = View.VISIBLE
            }
        }
        holder.itemView.setOnClickListener {
            onClickHistoricalHistory.clickMe(
                historical
            )
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        val txtValue: TextView = itemView.findViewById(R.id.txt_value)
        val txtName: TextView = itemView.findViewById(R.id.txt_name)
        val img: ImageView = itemView.findViewById(R.id.img_cash)
        val line: View = itemView.findViewById(R.id.line)

    }

    interface OnClickHistoricalHistory {
        fun clickMe(historical: Historical?)
    }

    init {
        this.arrayList = arrayList
        this.onClickHistoricalHistory = onClickHistoricalHistory
    }
}
