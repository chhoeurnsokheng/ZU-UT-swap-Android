package com.zillennium.utswap.module.account.logsScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListAccountLogsBinding
import com.zillennium.utswap.models.logs.Logs

class LogsAdapter(val item: List<Logs.AccountLogsData>)  : RecyclerView.Adapter<LogsAdapter.LogsViewHolder>(){
    inner class LogsViewHolder(val binding: ItemListAccountLogsBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bindData(listData: Logs.AccountLogsData) {
            binding.apply {
                txtTime.text = listData.addtime
                txtActionRemarks.text = listData.type
                txtActionIp.text = listData.addip
                if(listData.status.toString() == "1"){
                    txtStatus.text = "Normal"
                }else{
                    txtStatus.text = listData.status
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        return LogsViewHolder(
            ItemListAccountLogsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        holder.bindData(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }

}
