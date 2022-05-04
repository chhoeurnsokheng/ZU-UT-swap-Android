package com.zillennium.utswap.screens.wallet.historicalScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.Subscription.Subscription

class HistoricalFilterAdapter(context: Context, objects: List<*>) :
    ArrayAdapter<Any?>(context, 0, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.custom_historical_filter, parent, false)
        }
        val item = getItem(position) as Subscription?
        val dropDownTxt = convertView!!.findViewById<TextView>(R.id.txt_name_ut_historical)
        if (item != null) {
            dropDownTxt.text = item.str
        }
        return convertView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.custom_historical_filter_dropdown, parent, false)
        }
        val item = getItem(position) as Subscription?
        val dropDownTxt = convertView!!.findViewById<TextView>(R.id.txt_historical_dropdown)
        if (item != null) {
            dropDownTxt.text = item.str
        }
        return convertView
    }
}