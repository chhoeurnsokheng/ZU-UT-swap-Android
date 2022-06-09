package com.zillennium.utswap.screens.navbar.portfolioTab.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.portfolio.Change

class ChangeAdapter (
    arrayList: ArrayList<Change>,
) :
    RecyclerView.Adapter<ChangeAdapter.ViewHolder>() {
    private var arrayList: ArrayList<Change> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_performance, parent, false)
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
        internal val percent: TextView = itemView.findViewById(R.id.percent)
        internal val line: View = itemView.findViewById(R.id.line)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val change: Change = arrayList[position]

        holder.txtTitleProject.text = change.projectName
        holder.txtPercent.text = change.txtPercent.toString()

        if (change.txtPercent > 0)
        {
            holder.txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green))
            holder.percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green))
        }
        else
        {
            holder.txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
            holder.percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
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
                    holder.line.visibility = View.VISIBLE
                }
            }
        }

    }

    init {
        this.arrayList = arrayList
    }
}