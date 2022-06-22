package com.zillennium.utswap.screens.account.logsScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.logs.Logs

class LogsAdapter  (
    arrayList: ArrayList<Logs>,
) :
    RecyclerView.Adapter<LogsAdapter.ViewHolder>() {
    private var arrayList: ArrayList<Logs> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_account_logs, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val txtTime: TextView = itemView.findViewById(R.id.txt_time)
        internal val txtActionRemarks: TextView = itemView.findViewById(R.id.txt_action_remarks)
        internal val txtStatus: TextView = itemView.findViewById(R.id.txt_status)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val logs: Logs = arrayList[position]

        holder.txtTime.text = logs.txtTime
        holder.txtActionRemarks.text = logs.txtActionRemarks
        holder.txtStatus.text = logs.txtStatus

    }

    init {
        this.arrayList = arrayList
    }
}