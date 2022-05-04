package com.zillennium.utswap.screens.system.notificationScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R


class NotificationAdapter(private val notificationsData: ArrayList<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ivImage: ImageView = view.findViewById<View>(R.id.iv_image) as ImageView
        var linearLayout: LinearLayout = view.findViewById<View>(R.id.lay_item) as LinearLayout
        var txtDescription: TextView = view.findViewById<View>(R.id.txt_description) as TextView
        var txtTime: TextView = view.findViewById<View>(R.id.txt_time) as TextView
        var txtTitle: TextView = view.findViewById<View>(R.id.txt_title) as TextView

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_system_notification, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val notification = notificationsData[i]
        viewHolder.ivImage.setImageResource(notification.image)
        viewHolder.txtTitle.text = notification.title
        viewHolder.txtDescription.text = notification.description
        viewHolder.txtTime.text = notification.time
        viewHolder.linearLayout.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return notificationsData.size
    }
}
