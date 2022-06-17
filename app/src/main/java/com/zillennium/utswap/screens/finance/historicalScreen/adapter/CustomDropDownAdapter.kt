package com.zillennium.utswap.screens.finance.historicalScreen.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp

class CustomDropDownAdapter(context: Context, dataSource: List<String>) : ArrayAdapter<String>(context, 0, dataSource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val title = getItem(position)

        val view = LayoutInflater.from(context).inflate(R.layout.item_list_finance_historical_spinner_dropdown, parent, false)

        val txtTitle = view.findViewById<TextView>(R.id.txt_title)
        val layItem = view.findViewById<LinearLayout>(R.id.lay_item)

        txtTitle.text = title


        when(title){
            "BUY" -> {
                layItem.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
            }
            "SELL" -> {
                layItem.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
            }
            else -> {
                layItem.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
            }
        }

        return view
    }
}