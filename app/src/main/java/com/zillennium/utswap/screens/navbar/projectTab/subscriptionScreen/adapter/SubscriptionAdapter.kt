package com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.SubscriptionModel
import com.zillennium.utswap.utils.groupingSeparator
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class SubscriptionAdapter(arrayList: ArrayList<SubscriptionModel>, onclickAdapter: OnclickAdapter):
    RecyclerView.Adapter<SubscriptionAdapter.ViewHolder>()
{
    private var list_data: ArrayList<SubscriptionModel> = arrayList
    private val onclickAdapter: OnclickAdapter
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_title: TextView = view.findViewById<View>(R.id.tv_title) as TextView
        var tv_dollar: TextView = view.findViewById<View>(R.id.tv_dollar) as TextView
        var tv_day_lock: TextView = view.findViewById<View>(R.id.tv_day_lock) as TextView
        var tv_ut_value: TextView = view.findViewById<View>(R.id.tv_ut_value) as TextView
        var tv_ut_main_value: TextView = view.findViewById<View>(R.id.tv_ut_main_value) as TextView
        var determinateBar: ProgressBar = view.findViewById<View>(R.id.determinateBar) as ProgressBar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_navbar_project_subscription, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subscriptionList: SubscriptionModel = list_data[position]
        holder.tv_title.text = subscriptionList.tv_title
        holder.tv_dollar.text = groupingSeparator(subscriptionList.tv_dollar)
        holder.tv_day_lock.text = subscriptionList.tv_day_lock
        holder.tv_ut_value.text = groupingSeparator(subscriptionList.tv_ut_value)

        holder.tv_ut_main_value.text = groupingSeparator(subscriptionList.tv_ut_main_value)

        holder.itemView.setOnClickListener{
            onclickAdapter.onClickMe(subscriptionList)
        }
        holder.determinateBar.progress = (subscriptionList.tv_ut_value.toInt() * 100)/subscriptionList.tv_ut_main_value.toInt()
    }

    override fun getItemCount(): Int {
        return list_data.size
    }

    interface OnclickAdapter{
        fun onClickMe(subscriptionModel: SubscriptionModel)
    }

    init {
        this.list_data = arrayList
        this.onclickAdapter = onclickAdapter
    }
}




