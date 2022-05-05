package com.zillennium.utswap.screens.wallet.transactionScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.screens.wallet.transactionScreen.model.MyWalletGroup

class MyWalletGroupAdapter(arrayList: ArrayList<MyWalletGroup>) :
    RecyclerView.Adapter<MyWalletGroupAdapter.ViewHolder>() {
    private var arrayList: ArrayList<MyWalletGroup> = ArrayList<MyWalletGroup>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_wallet_balance_group, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myWalletGroup: MyWalletGroup = arrayList[position]
        holder.txtDayGroup.text = myWalletGroup.selectDayGroup
        val myWalletItemAdapter = MyWalletItemAdapter(myWalletGroup.selectItemGroup)
        holder.rcItemGroupDay.adapter = myWalletItemAdapter
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDayGroup: TextView = itemView.findViewById(R.id.txt_day_group)
        val rcItemGroupDay: RecyclerView = itemView.findViewById(R.id.rc_item_group_day)

    }

    init {
        this.arrayList = arrayList
    }
}