package com.zillennium.utswap.screens.setting.balanceScreen.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.BalanceHistory.BalanceHistory

class BalanceHistoryAdapter(
    arrayList: ArrayList<BalanceHistory>,
    onclickBalanceHistory: OnclickBalanceHistory
) :
    RecyclerView.Adapter<BalanceHistoryAdapter.ViewHolder>() {
    private var arrayList: ArrayList<BalanceHistory> = ArrayList()
    private val onclickBalanceHistory: OnclickBalanceHistory
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_wallet_balance_history, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val balanceHistory: BalanceHistory = arrayList[position]
        holder.img.setImageResource(balanceHistory.imagePath)
        holder.txtDate.text = balanceHistory.date
        holder.txtName.text = balanceHistory.transactionDetail
        //
        if (balanceHistory.moneyIn.equals(" ")) {
            holder.txtMoney.text = balanceHistory.moneyOut
        } else if (!balanceHistory.moneyIn.equals(" ")) {
            holder.txtMoney.setTextColor(Color.GREEN)
            holder.img.imageTintList = ColorStateList.valueOf(Color.GREEN)
            holder.txtMoney.text = balanceHistory.moneyIn
        }
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
            onclickBalanceHistory.onClickMe(
                balanceHistory
            )
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        internal val img: ImageView = itemView.findViewById(R.id.img_cash)
        internal val line: View = itemView.findViewById(R.id.line)
        internal val txtMoney: TextView = itemView.findViewById(R.id.txt_money)
        internal val txtName: TextView = itemView.findViewById(R.id.txt_name)
        internal val container: LinearLayout = itemView.findViewById(R.id.container_balance_history)
    }

    interface OnclickBalanceHistory {
        fun onClickMe(balanceHistory: BalanceHistory?)
    }

    init {
        this.arrayList = arrayList
        this.onclickBalanceHistory = onclickBalanceHistory
    }
}