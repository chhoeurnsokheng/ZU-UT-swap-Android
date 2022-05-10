package com.zillennium.utswap.screens.navbar.tradeTab

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp

class TradeAdapter(arrayList: ArrayList<Trade>) :
    RecyclerView.Adapter<TradeAdapter.ViewHolder>() {
    private val listdata: ArrayList<Trade> = arrayList

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var linearLayout: LinearLayout = view.findViewById<View>(R.id.linear_layout) as LinearLayout
        var txtProject: TextView = view.findViewById<View>(R.id.txt_project) as TextView
        var txtChange: TextView = view.findViewById<View>(R.id.txt_change) as TextView
        var txtLast: TextView = view.findViewById<View>(R.id.txt_last) as TextView
        var txtVolume: TextView = view.findViewById<View>(R.id.txt_volume) as TextView
        var viewLine: View = view.findViewById<View>(R.id.view_line) as View
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_trade, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val tradeList: Trade = listdata[i]
        viewHolder.txtProject.text = tradeList.project

        if(tradeList.change < 0){
            viewHolder.txtChange.text = tradeList.change.toString() + "%"
        }else {
            viewHolder.txtChange.text = "+" + tradeList.change + "%"
            viewHolder.txtChange.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
        }

        viewHolder.txtLast.text = "$" + tradeList.last
        viewHolder.txtVolume.text = tradeList.volume.toString()

        if(i + 1 == listdata.size){
            viewHolder.viewLine.visibility = GONE
        }

        viewHolder.linearLayout.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

}