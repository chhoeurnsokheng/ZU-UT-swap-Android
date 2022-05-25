package com.zillennium.utswap.screens.navbar.tradeTab.fragment.Transactions.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.orders.Orders

class TransactionsAdapter (
    arrayList: ArrayList<Orders>,
) :
    RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {
    private var arrayList: ArrayList<Orders> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_orders, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val txtStatus: TextView = itemView.findViewById(R.id.txt_status)
        internal val txtDateBuy: TextView = itemView.findViewById(R.id.txt_date)
        internal val txtUT: TextView = itemView.findViewById(R.id.txt_ut)
        internal val txtPrice: TextView = itemView.findViewById(R.id.txt_price)
        internal val line: View = itemView.findViewById(R.id.line)
        internal val imgArrow: LinearLayout = itemView.findViewById(R.id.img_arrow)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orders: Orders = arrayList[position]

        holder.txtUT.text = orders.txtUT
        holder.txtPrice.text = "$ ${orders.txtPrice}"
        holder.txtDateBuy.text = orders.txtDate

        if(orders.txtStatus == "SELL")
        {
            holder.txtStatus.text = orders.txtStatus
            holder.txtStatus.setTextColor(Color.RED)
        }
        else{
            holder.txtStatus.text = orders.txtStatus
        }

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
                    //holder.itemView.setBackgroundResource(R.color.white)
                    holder.line.visibility = View.VISIBLE
                }
            }
        }

        holder.imgArrow.visibility = View.GONE
    }

    init {
        this.arrayList = arrayList
    }
}