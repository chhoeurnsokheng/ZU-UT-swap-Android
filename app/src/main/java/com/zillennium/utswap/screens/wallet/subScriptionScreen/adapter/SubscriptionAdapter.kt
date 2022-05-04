package com.zillennium.utswap.screens.wallet.subScriptionScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.Subscription.Subscription


class SubscriptionAdapter(context: Context, objects: List<*>) :
    ArrayAdapter<Any?>(context, 0, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.customer_in_subscription, parent, false)
        }
        val item = getItem(position) as Subscription?
        val dropDownTxt = convertView!!.findViewById<TextView>(R.id.txt_name_ut_spinner)
        if (item != null) {
            dropDownTxt.text = item.str
        }
        return convertView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.custom_dropdown_in_subscription, parent, false)
        }
        val item = getItem(position) as Subscription?
        val dropDownTxt = convertView!!.findViewById<TextView>(R.id.txt_name_ut)
        if (item != null) {
            dropDownTxt.text = item.str
        }
        return convertView
    }
}