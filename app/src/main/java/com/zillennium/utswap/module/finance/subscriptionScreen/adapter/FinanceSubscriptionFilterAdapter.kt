package com.zillennium.utswap.module.finance.subscriptionScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.models.financeSubscription.SubscriptionObject
import com.zillennium.utswap.module.finance.subscriptionScreen.FinanceSubscriptionsActivity

class FinanceSubscriptionFilterAdapter(
    var titleList: ArrayList<SubscriptionObject.ProjectList>,
    var onClickAdapter: OnClickAdapter
) :
    RecyclerView.Adapter<FinanceSubscriptionFilterAdapter.ViewHolder>() {


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
        holder.titleFilter.text = titleList[position].name
        if (position == titleList.size - 1) {
            holder.line.visibility = View.GONE
        }
        if (FinanceSubscriptionsActivity.titleProject == titleList[position].name) {
            holder.layIconCheck.visibility = View.VISIBLE
        }
        holder.layoutFilter.setOnClickListener {
            onClickAdapter.onClickMe(titleList[position].project, titleList[position].name)


        }

    }

    override fun getItemCount(): Int {
        return titleList.size
    }

    interface OnClickAdapter {
        fun onClickMe(projectId: String, projectName: String)
    }


}