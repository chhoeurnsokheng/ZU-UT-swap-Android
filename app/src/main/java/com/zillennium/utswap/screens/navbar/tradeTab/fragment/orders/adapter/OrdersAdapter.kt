package com.zillennium.utswap.screens.navbar.tradeTab.fragment.orders.adapter

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

class OrdersAdapter (
    arrayList: ArrayList<Orders>,
    onClickDelete: OnClickDelete
) :
    RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {
    private var arrayList: ArrayList<Orders> = ArrayList()
    private var onClickDelete: OnClickDelete
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
        internal val btnArrow: LinearLayout = itemView.findViewById(R.id.img_arrow)
        internal val btnDelete: LinearLayout = itemView.findViewById(R.id.btn_delete)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orders: Orders = arrayList[position]

        holder.txtUT.text = orders.txtUT.toString()
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

        holder.itemView.setOnClickListener {
            holder.btnArrow.visibility = View.VISIBLE
            holder.btnDelete.visibility = View.GONE
        }

        holder.btnArrow.setOnClickListener {
            holder.btnArrow.visibility = View.GONE
            holder.btnDelete.visibility = View.VISIBLE

            holder.btnDelete.setOnClickListener {
                onClickDelete.clickMe()
                holder.btnDelete.visibility = View.GONE
                holder.btnArrow.visibility = View.VISIBLE
            }
        }
    }

    init {
        this.arrayList = arrayList
        this.onClickDelete = onClickDelete
    }

    interface OnClickDelete{
        fun clickMe()
    }
}