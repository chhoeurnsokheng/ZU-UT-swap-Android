package com.zillennium.utswap.screens.finance.historicalScreen.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.models.financeHistorical.FinanceHistoricalFilterModel


class FinanceHistoricalFilterAdapter(
    arrayList: ArrayList<FinanceHistoricalFilterModel>,
    onClickAdapter: OnClickAdapter
) : RecyclerView.Adapter<FinanceHistoricalFilterAdapter.ViewHolder>() {

    private var listData: ArrayList<FinanceHistoricalFilterModel> = arrayList
    private var onClickAdapter: OnClickAdapter
    private var currentSelectedIndex = -1

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var titleFilter: TextView = view.findViewById<View>(R.id.title_filter) as TextView
        var line: View = view.findViewById(R.id.line) as View
        var layIconCheck: LinearLayout = view.findViewById(R.id.lay_icon_check) as LinearLayout
        var layoutFilter: LinearLayout = view.findViewById(R.id.layout_filter) as LinearLayout

       /* fun bind(financeHistoricalFilterModel: FinanceHistoricalFilterModel, position: Int) {

            if (financeHistoricalFilterModel.isCheck) {
                layIconCheck.visibility = View.VISIBLE
            } else {
                layIconCheck.visibility = View.GONE
            }
            println("===============================")
            println(financeHistoricalFilterModel.isCheck)

            layoutFilter.setOnLongClickListener { (markSelectedItem(position)) }

        }

        @SuppressLint("NotifyDataSetChanged")
        private fun markSelectedItem(index: Int): Boolean {
            for (item in listData) {
                item.isCheck = false
            }

            listData[index].isCheck = true
            currentSelectedIndex = index
            println("==================== mark Selected Item")
            println(listData[index].isCheck)
            notifyDataSetChanged()
            return true
        }*/

    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): FinanceHistoricalFilterAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_finance_historical_filter, viewGroup, false)
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val financeSubscriptionFilterList: FinanceHistoricalFilterModel = listData[position]
        holder.titleFilter.text = financeSubscriptionFilterList.titleHistorical
        if (listData.size == 1) {
            holder.line.visibility = View.GONE
        } else {
            when (position) {
                listData.size - 1 -> {
                    holder.line.visibility = View.GONE
                }
                0 -> {
                    holder.line.visibility = View.VISIBLE
                }
                else -> {
                    holder.line.visibility = View.VISIBLE
                }
            }
        }

        if(SettingVariable.finance_historical_filter.value == financeSubscriptionFilterList.titleHistorical){
            holder.layIconCheck.visibility = View.VISIBLE
        }

        holder.layoutFilter.setOnClickListener {
            onClickAdapter.onClickMe(financeSubscriptionFilterList)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    interface OnClickAdapter {
        fun onClickMe(financeHistoricalFilterModel: FinanceHistoricalFilterModel)
    }

    init {
        this.onClickAdapter = onClickAdapter
    }
}