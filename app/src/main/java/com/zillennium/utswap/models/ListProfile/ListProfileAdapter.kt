package com.zillennium.utswap.models.ListProfile


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.zillennium.utswap.R


class ListProfileAdapter(context: Context?, profileArrayList: ArrayList<ListProfile>) :
    ArrayAdapter<ListProfile?>(
        context!!, R.layout.item_list_setting_profile,
        profileArrayList as List<ListProfile?>
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val profile = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.item_list_setting_profile, parent, false)
        }
        val txtTitle = convertView!!.findViewById<TextView>(R.id.txt_title)
        val txtDesc = convertView.findViewById<TextView>(R.id.txt_desc)
        txtTitle.text = profile!!.title
        txtDesc.text = profile.desc
        return convertView
        //        return super.getView(position, convertView, parent);
    }
}