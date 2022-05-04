package com.zillennium.utswap.screens.wallet.depositScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.PaymentMethod.CustomItem

class CustomAdapter(context: Context, customList: ArrayList<CustomItem>) :
    ArrayAdapter<Any?>(context, 0, customList!! as List<Any?>) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView =
                LayoutInflater.from(context).inflate(R.layout.custom_spinner, parent, false)
        }
        val item = getItem(position) as CustomItem?
        val spinnerImg = convertView!!.findViewById<ImageView>(R.id.img_logo_payment_spinner)
        val spinnerTxt = convertView.findViewById<TextView>(R.id.txt_payment_method_spinner)
        if (item != null) {
            spinnerImg.setImageResource(item.spinnerItemImage)
            spinnerTxt.text = item.spinnerItemName
        }
        return convertView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.custom_dropdown_payment_method, parent, false)
        }
        val item = getItem(position) as CustomItem?
        val dropDownImg = convertView!!.findViewById<ImageView>(R.id.img_logo_payment)
        val dropDownTxt = convertView.findViewById<TextView>(R.id.txt_payment_method)
        if (item != null) {
            dropDownImg.setImageResource(item.spinnerItemImage)
            dropDownTxt.text = item.spinnerItemName
        }
        return convertView
    }
}
