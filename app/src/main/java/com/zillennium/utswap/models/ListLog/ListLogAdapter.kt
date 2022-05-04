package com.zillennium.utswap.models.ListLog


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.zillennium.utswap.R


class ListLogAdapter(context: Context?, profileArrayList: ArrayList<ListLog>) :
    ArrayAdapter<ListLog?>(
        context!!, R.layout.item_list_setting_log,
        profileArrayList as List<ListLog?>
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val profile = getItem(position)
        if (convertView == null) {
            convertView =
                LayoutInflater.from(context).inflate(R.layout.item_list_setting_log, parent, false)
        }
        val txtOperationtime = convertView!!.findViewById<TextView>(R.id.txt_operationtime)
        val txtActionRemark = convertView.findViewById<TextView>(R.id.txt_action_remark)
        val txtStatus = convertView.findViewById<TextView>(R.id.txt_status)
        txtOperationtime.text = profile!!.operatingtime
        txtActionRemark.text = profile.actionRemark
        txtStatus.text = profile.status
        return convertView
        //        return super.getView(position, convertView, parent);
    }
}
