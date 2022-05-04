package com.zillennium.utswap.models.ListLanguage


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.zillennium.utswap.R


class ListLanguageAdapter(context: Context?, accountArrayList: ArrayList<ListLanguage>) :
    ArrayAdapter<ListLanguage?>(
        context!!, R.layout.item_list_setting_language,
        accountArrayList!! as List<ListLanguage?>
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val language = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.item_list_setting_language, parent, false)
        }
        val txtTitle = convertView!!.findViewById<TextView>(R.id.txt_title)
        val ivIcon = convertView.findViewById<ImageView>(R.id.iv_icon)
        val iconRight = convertView.findViewById<ImageView>(R.id.icon_right)
        txtTitle.text = language!!.title
        ivIcon.setImageResource(language.icon)
        if (language.selected) {
            iconRight.setImageResource(R.drawable.ic_circle_check_regular)
        }
        return convertView
        //        return super.getView(position, convertView, parent);
    }
}
