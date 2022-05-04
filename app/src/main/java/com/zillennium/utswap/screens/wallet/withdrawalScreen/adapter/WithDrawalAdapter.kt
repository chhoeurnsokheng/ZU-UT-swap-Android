package com.zillennium.utswap.screens.wallet.withdrawalScreen.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.WithDrawalHistory.WithDrawalHistory


class WithDrawalAdapter(
    arrayList: ArrayList<WithDrawalHistory>,
    onclickWithDrawHistory: OnclickWithDrawHistory
) :
    RecyclerView.Adapter<WithDrawalAdapter.ViewHolder>() {
    private var arrayList = ArrayList<WithDrawalHistory>()
    private val onclickWithDrawHistory: OnclickWithDrawHistory
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_wallet_withdraw_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val withDrawalHistory = arrayList[position]
        holder.txtAmount.text = withDrawalHistory.amount
        holder.txtDate.text = withDrawalHistory.time
        if (arrayList.size == 1) {
            holder.line.visibility = View.GONE
        } else {
            when (position) {
                arrayList.size - 1 -> {
                    holder.line.visibility = View.GONE
                }
                0 -> {
                    holder.itemView.setBackgroundResource(R.drawable.card_view_whtie_border_top)
                    holder.line.visibility = View.VISIBLE
                }
                else -> {
                    holder.itemView.setBackgroundResource(R.color.white)
                    holder.line.visibility = View.VISIBLE
                }
            }
        }
        holder.itemView.setOnClickListener {
            onclickWithDrawHistory.onClickMe(
                withDrawalHistory
            )
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAmount: TextView = itemView.findViewById(R.id.txt_amount)
        val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        val line: View = itemView.findViewById(R.id.line)

    }

    interface OnclickWithDrawHistory {
        fun onClickMe(withDrawalHistory: WithDrawalHistory?)
    }

    init {
        this.arrayList = arrayList
        this.onclickWithDrawHistory = onclickWithDrawHistory
    }
}