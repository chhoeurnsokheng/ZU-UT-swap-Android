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

class LogsAdapter  : BaseRecyclerViewAdapterGeneric<Logs.AccountLogsData, LogsAdapter.LogsViewHolder>(){
    inner class LogsViewHolder(root: ItemListAccountLogsBinding) :BaseViewHolder<ItemListAccountLogsBinding>(root) {
        fun bindData(listData: Logs.AccountLogsData) {
            binding.apply {
                txtTime.text = listData.addtime
                txtActionRemarks.text = listData.type
                txtStatus.text = listData.status
            }
        }

    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = LogsViewHolder(ItemListAccountLogsBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(holder: LogsViewHolder, position: Int, context: Context) {
       holder.bindData(items[position])
    }

}
//    (
//    arrayList: ArrayList<Logs>,
//) :
//    RecyclerView.Adapter<LogsAdapter.ViewHolder>() {
//    private var arrayList: ArrayList<Logs> = ArrayList()
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ViewHolder {
//        val view: View = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_list_account_logs, parent, false)
//        return ViewHolder(
//            view
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return arrayList.size
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        internal val txtTime: TextView = itemView.findViewById(R.id.txt_time)
//        internal val txtActionRemarks: TextView = itemView.findViewById(R.id.txt_action_remarks)
//        internal val txtStatus: TextView = itemView.findViewById(R.id.txt_status)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val logs: Logs = arrayList[position]
//
//        holder.txtTime.text = logs.txtTime
//        holder.txtActionRemarks.text = logs.txtActionRemarks
//        holder.txtStatus.text = logs.txtStatus
//
//    }
//
//    init {
//        this.arrayList = arrayList
//    }
//}