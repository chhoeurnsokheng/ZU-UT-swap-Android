package com.zillennium.utswap.screens.wallet.transferScreen.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.TransferHistory.TransferHistory


class TransferAdapter(
    arrayList: ArrayList<TransferHistory>,
    onclickTransferHistory: OnclickTransferHistory
) :
    RecyclerView.Adapter<TransferAdapter.ViewHolder>() {
    private var arrayList = ArrayList<TransferHistory>()
    private val onclickTransferHistory: OnclickTransferHistory
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_wallet_transfer_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transferHistory = arrayList[position]
        holder.txtTime.text = transferHistory.time
        holder.txtAmount.text = transferHistory.amount
        holder.txtReceiver.text = transferHistory.receiver
        if (arrayList.size == 1) {
            holder.line.visibility = View.GONE
        } else {
            when (position) {
                arrayList.size - 1 -> {
                    holder.line.visibility = View.GONE
                }
                0 -> {
                    holder.itemView.setBackgroundResource(R.drawable.card_view_whtie_border_top)
                    holder.line.visibility = View.VISIBLE
                }
                else -> {
                    holder.itemView.setBackgroundResource(R.color.white)
                    holder.line.visibility = View.VISIBLE
                }
            }
        }
        holder.itemView.setOnClickListener {
            onclickTransferHistory.onClickMe(
                transferHistory
            )
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAmount: TextView = itemView.findViewById(R.id.txt_amount)
        val txtReceiver: TextView = itemView.findViewById(R.id.txt_receiver)
        val txtTime: TextView = itemView.findViewById(R.id.txt_date)
        val line: View = itemView.findViewById(R.id.line)

    }

    interface OnclickTransferHistory {
        fun onClickMe(transferHistory: TransferHistory?)
    }

    init {
        this.arrayList = arrayList
        this.onclickTransferHistory = onclickTransferHistory
    }
}
