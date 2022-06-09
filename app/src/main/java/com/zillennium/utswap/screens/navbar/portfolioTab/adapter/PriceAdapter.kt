package com.zillennium.utswap.screens.navbar.portfolioTab.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.portfolio.Price

class PriceAdapter (
    arrayList: ArrayList<Price>,
) :
    RecyclerView.Adapter<PriceAdapter.ViewHolder>() {
    private var arrayList: ArrayList<Price> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_price, parent, false)
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
        val price: Price = arrayList[position]

        holder.txtTitleProject.text = price.projectName
        holder.txtBuy.text = price.txtBuy.toString()
        holder.txtMkt.text = price.txtMkt.toString()

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

        if(position == 2)
        {
            holder.txtMkt.setTextColor(Color.parseColor("#FF0000"))
        }

    }

    init {
        this.arrayList = arrayList
    }
}