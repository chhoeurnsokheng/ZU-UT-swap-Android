package com.zillennium.utswap.screens.system.notification.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.notification.Notification
import com.zillennium.utswap.utils.dpToPx

class NotificationAdapter (
    arrayList: ArrayList<Notification>,
) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    private var arrayList: ArrayList<Notification> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_system_notification, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val txtTitle: TextView = itemView.findViewById(R.id.txt_title)
        internal val txtDescription: TextView = itemView.findViewById(R.id.txt_description)
        internal val txtDuration: TextView = itemView.findViewById(R.id.txt_duration)
        internal val line: View = itemView.findViewById(R.id.line)
        internal val linearContainer: LinearLayout = itemView.findViewById(R.id.linear_container)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification: Notification = arrayList[position]

        holder.txtTitle.text = notification.txtTitle
        holder.txtDescription.text = notification.txtDescription
        holder.txtDuration.text = notification.txtDuration


        if (arrayList.size == 1) {
            holder.line.visibility = View.GONE
        } else {
            when (position) {
                arrayList.size - 1 -> {
                    holder.line.visibility = View.GONE
                }
                0 -> {
                    holder.line.visibility = View.VISIBLE
                    holder.linearContainer.setPadding(dpToPx(20), dpToPx(30), dpToPx(20), 0)
                }
                else -> {
                    holder.line.visibility = View.VISIBLE
                }
            }
        }
    }

    init {
        this.arrayList = arrayList
    }

}