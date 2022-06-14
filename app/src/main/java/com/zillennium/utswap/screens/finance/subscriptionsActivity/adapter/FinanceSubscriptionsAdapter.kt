package com.zillennium.utswap.screens.finance.subscriptionsActivity.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.financeSubscription.FinanceSubscriptionFilterModel
import com.zillennium.utswap.models.financeSubscription.FinanceSubscriptionsModel
import com.zillennium.utswap.utils.groupingSeparator

class FinanceSubscriptionsAdapter(
    arrayList: ArrayList<FinanceSubscriptionsModel>,
    onClickAdapter: OnClickAdapter
) :
    RecyclerView.Adapter<FinanceSubscriptionsAdapter.ViewHolder>() {

    private var listData: ArrayList<FinanceSubscriptionsModel> = arrayList
    private val onClickAdapter: OnClickAdapter

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageSubscription: ImageView = view.findViewById<View>(R.id.image_subscription) as ImageView
        var titleSubscriptions: TextView = view.findViewById<View>(R.id.title_subscription) as TextView
        var dateSubscription: TextView = view.findViewById<View>(R.id.date_subscription) as TextView
        var amountSubscription: TextView = view.findViewById<View>(R.id.amount_subscription) as TextView
        var statusDays: TextView = view.findViewById(R.id.status_days) as TextView
        var layoutSubscription: LinearLayout = view.findViewById<View>(R.id.linear_subscription) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_finance_subscriptions, viewGroup, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val financeSubscriptionList: FinanceSubscriptionsModel = listData[position]
        holder.imageSubscription.setImageResource(financeSubscriptionList.imageSubscription)
        holder.titleSubscriptions.text = financeSubscriptionList.titleSubscription
        holder.dateSubscription.text = financeSubscriptionList.dateSubscription
        holder.amountSubscription.text = "$" + groupingSeparator(financeSubscriptionList.amount)
        holder.statusDays.text = financeSubscriptionList.durationDay
        holder.layoutSubscription.setOnClickListener {
            onClickAdapter.onClickMe(financeSubscriptionList)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: ArrayList<FinanceSubscriptionsModel>) {
        listData = filterList
        notifyDataSetChanged()
    }

    interface OnClickAdapter {
        fun onClickMe(financeSubscriptionsModel: FinanceSubscriptionsModel)
    }

    init{
        this.onClickAdapter = onClickAdapter
    }
}


