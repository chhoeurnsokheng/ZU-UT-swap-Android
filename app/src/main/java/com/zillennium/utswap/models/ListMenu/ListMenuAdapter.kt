package com.zillennium.utswap.models.ListMenu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.zillennium.utswap.R


class ListMenuAdapter(context: Context?, accountArrayList: ArrayList<ListMenu>) :
    ArrayAdapter<ListMenu?>(
        context!!, R.layout.item_list_setting_menu,
        accountArrayList!! as List<ListMenu?>
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val account = getItem(position)
        if (convertView == null) {
            convertView =
                LayoutInflater.from(context).inflate(R.layout.item_list_setting_menu, parent, false)
        }
        val txtTitle = convertView!!.findViewById<TextView>(R.id.txt_title)
        val ivIcon = convertView.findViewById<ImageView>(R.id.iv_icon)
        txtTitle.text = account!!.title
        ivIcon.setImageResource(account.icon)
        return convertView
        //        return super.getView(position, convertView, parent);
    }
}