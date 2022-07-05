package com.zillennium.utswap.module.finance.subscriptionScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.models.financeSubscription.FinanceSubscriptionFilterModel

class FinanceSubscriptionFilterAdapter(arrayList: ArrayList<FinanceSubscriptionFilterModel>, onClickAdapter: OnClickAdapter) :
    RecyclerView.Adapter<FinanceSubscriptionFilterAdapter.ViewHolder>() {

    private var listData: ArrayList<FinanceSubscriptionFilterModel> = arrayList
    private var onClickAdapter: OnClickAdapter

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleFilter: TextView = view.findViewById<View>(R.id.title_filter) as TextView
        var line: View = view.findViewById(R.id.line) as View
        var layIconCheck: LinearLayout = view.findViewById(R.id.lay_icon_check) as LinearLayout
        var layoutFilter: LinearLayout = view.findViewById(R.id.layout_filter) as LinearLayout
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): FinanceSubscriptionFilterAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_finance_subscriptions_filter, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val financeSubscriptionFilterList: FinanceSubscriptionFilterModel = listData[position]
        holder.titleFilter.text = financeSubscriptionFilterList.titleFilter

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

        if (SettingVariable.finance_subscription_filter.value == financeSubscriptionFilterList.titleFilter){
            holder.layIconCheck.visibility = View.VISIBLE
        }


        holder.layoutFilter.setOnClickListener {
            onClickAdapter.onClickMe(financeSubscriptionFilterList)
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    interface OnClickAdapter{
        fun onClickMe(financeSubscriptionFilterModel: FinanceSubscriptionFilterModel)
    }

    init {
        this.onClickAdapter = onClickAdapter
    }

}