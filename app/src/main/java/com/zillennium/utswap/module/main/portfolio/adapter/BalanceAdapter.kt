package com.zillennium.utswap.module.main.portfolio.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.portfolio.Balance

class BalanceAdapter (
    arrayList: ArrayList<Balance>,
) :
    RecyclerView.Adapter<BalanceAdapter.ViewHolder>() {
    private var arrayList: ArrayList<Balance> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_portfolio_price, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val txtTitleProject: TextView = itemView.findViewById(R.id.txt_title_project)
        internal val line: View = itemView.findViewById(R.id.line)
        internal val txtBuy : TextView = itemView.findViewById(R.id.txt_buy)
        internal val txtMkt: TextView = itemView.findViewById(R.id.txt_mkt)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val balance: Balance = arrayList[position]

        holder.txtTitleProject.text = balance.projectName
        holder.txtBuy.text = balance.ut.toString()
        holder.txtMkt.text = balance.value.toString()
        holder.txtMkt.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_222222))

        if (arrayList.size == 1) {
            holder.line.visibility = View.GONE
        } else {
            when (position) {
                arrayList.size - 1 -> {
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

    }

    init {
        this.arrayList = arrayList
    }
}