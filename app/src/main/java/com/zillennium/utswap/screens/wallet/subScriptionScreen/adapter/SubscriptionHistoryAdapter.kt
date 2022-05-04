package com.zillennium.utswap.screens.wallet.subScriptionScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.SubscriptionHistory.SubscriptionHistory


class SubscriptionHistoryAdapter(
    arrayList: ArrayList<SubscriptionHistory>,
    onclickSubscriptionHistory: OnclickSubscriptionHistory
) :
    RecyclerView.Adapter<SubscriptionHistoryAdapter.ViewHolder>() {
    private var arrayList = ArrayList<SubscriptionHistory>()
    private val onclickSubscriptionHistory: OnclickSubscriptionHistory
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_wallet_subscription_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subscriptionHistory = arrayList[position]
        holder.txtNameSub.text = subscriptionHistory.nameSub
        holder.txtValue.text = subscriptionHistory.value
        holder.txtDate.text = subscriptionHistory.date
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
            onclickSubscriptionHistory.onClickMe(
                subscriptionHistory
            )
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNameSub: TextView = itemView.findViewById(R.id.txt_nameSub)
        val txtValue: TextView = itemView.findViewById(R.id.txt_value)
        val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        val line: View = itemView.findViewById(R.id.line)

    }

    interface OnclickSubscriptionHistory {
        fun onClickMe(subscriptionHistory: SubscriptionHistory?)
    }

    init {
        this.arrayList = arrayList
        this.onclickSubscriptionHistory = onclickSubscriptionHistory
    }
}