package com.zillennium.utswap.screens.wallet.depositScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.DepositHistory.DepositHistory

class DepositAdapter(
    arrayList: ArrayList<DepositHistory>,
    onclickDepositHistory: OnclickDepositHistory
) :
    RecyclerView.Adapter<DepositAdapter.ViewHolder>() {
    private var arrayList = ArrayList<DepositHistory>()
    private val onclickDepositHistory: OnclickDepositHistory
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_wallet_deposit_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val depositHistory = arrayList[position]
        holder.img.setImageResource(depositHistory.imagePath)
        holder.txtAmount.text = depositHistory.amount
        holder.txtDate.text = depositHistory.rechargeTime
        holder.txtStatus.text = depositHistory.status
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
        holder.itemView.setOnClickListener { onclickDepositHistory.onClickMe(depositHistory) }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img_success)
        val line: View = itemView.findViewById(R.id.line)
        val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        val txtAmount: TextView = itemView.findViewById(R.id.txt_amount)
        val txtStatus: TextView = itemView.findViewById(R.id.txt_status)

    }

    interface OnclickDepositHistory {
        fun onClickMe(depositHistory: DepositHistory?)
    }

    init {
        this.arrayList = arrayList
        this.onclickDepositHistory = onclickDepositHistory
    }
}