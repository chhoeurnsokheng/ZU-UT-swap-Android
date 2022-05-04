package com.zillennium.utswap.models.ListLockScreen


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.zillennium.utswap.R


class ListLockScreenAdapter(context: Context?, lockScreenArrayList: ArrayList<ListLockScreen>) :
    ArrayAdapter<ListLockScreen?>(
        context!!, R.layout.item_list_setting_menu,
        lockScreenArrayList as List<ListLockScreen?>
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val lockScreen = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.item_list_setting_lockscreen, parent, false)
        }
        val txtTitle = convertView!!.findViewById<TextView>(R.id.txt_title)
        txtTitle.text = lockScreen!!.title
        if (lockScreen.selected) {
            val layItem = convertView.findViewById<LinearLayout>(R.id.lay_item)
            val ivIcon = convertView.findViewById<ImageView>(R.id.iv_icon)
            layItem.setBackgroundResource(R.drawable.bg_lockscreen_selected)
            ivIcon.setColorFilter(convertView.resources.getColor(R.color.white))
            txtTitle.setTextColor(convertView.resources.getColor(R.color.white))
        }
        return convertView
        //        return super.getView(position, convertView, parent);
    }
}