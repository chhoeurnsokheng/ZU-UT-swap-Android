package com.zillennium.utswap.module.finance.transferScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.FinanceTransferModel

class TransferAdapter(private val arrayList: ArrayList<FinanceTransferModel>): RecyclerView.Adapter<TransferAdapter.ViewHolder>() {

    private val listData: ArrayList<FinanceTransferModel> = arrayList

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var layTransferItem: LinearLayout = view.findViewById(R.id.layTransferItem) as LinearLayout
        var user_Profile: ImageView = view.findViewById(R.id.iv_user_profile) as ImageView
        val user_Name: TextView = view.findViewById(R.id.tv_user_name) as TextView
        var user_PhoneNumber: TextView = view.findViewById(R.id.tv_receive_phoneNumber) as TextView
        var view_line :View =view.findViewById<View>(R.id.view_line)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_finance_transfer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transferCurrentItemList: FinanceTransferModel = listData[position]



        holder.user_Profile.setImageResource(transferCurrentItemList.userProfile)
        holder.user_Name.text = transferCurrentItemList.userName
        holder.user_PhoneNumber.text = transferCurrentItemList.phoneNumber

        if (position ==0){
            holder.view_line.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }
}