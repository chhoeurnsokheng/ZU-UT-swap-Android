package com.zillennium.utswap.screens.navbar.portfolioTab.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.portfolio.Weight

class WeightAdapter (
    arrayList: ArrayList<Weight>,
) :
    RecyclerView.Adapter<WeightAdapter.ViewHolder>() {
    private var arrayList: ArrayList<Weight> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_portfolio_performance, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val txtTitleProject: TextView = itemView.findViewById(R.id.txt_title_project)
        internal val txtPercent: TextView = itemView.findViewById(R.id.txt_percent)
        internal val line: View = itemView.findViewById(R.id.line)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weight: Weight = arrayList[position]

        holder.txtTitleProject.text = weight.projectName
        holder.txtPercent.text = weight.txtPercent.toString()

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