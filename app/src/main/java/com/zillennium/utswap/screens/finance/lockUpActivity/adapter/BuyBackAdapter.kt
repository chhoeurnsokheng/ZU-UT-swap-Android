package com.zillennium.utswap.screens.finance.lockUpActivity.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.FinanceBalanceModel
import com.zillennium.utswap.models.financeLockUp.BuyBackModel
import com.zillennium.utswap.screens.finance.balanceActivity.adapter.FinanceBalanceAdapter
import com.zillennium.utswap.utils.groupingSeparator
import java.text.NumberFormat

class BuyBackAdapter(arrayList: ArrayList<BuyBackModel>, onClickAdapter: OnClickAdapter)
    :RecyclerView.Adapter<BuyBackAdapter.ViewHolder>(){

    private var arrayList: ArrayList<BuyBackModel> = ArrayList()
    private var onClickAdapter: OnClickAdapter

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        internal val txtTitle: TextView = view.findViewById<View>(R.id.txt_title) as TextView
        internal val txtAmount: TextView = view.findViewById<View>(R.id.txt_amount) as TextView
        internal val txtDateStart: TextView = view.findViewById<View>(R.id.txt_date_start) as TextView
        internal val txtDateEnd: TextView = view.findViewById<View>(R.id.txt_date_end) as TextView
        internal val txtDuration: TextView = view.findViewById<View>(R.id.txt_duration) as TextView
        internal val layoutBuyBack: LinearLayout = view.findViewById<View>(R.id.linear_buy_back) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BuyBackAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_finance_lock_up_buy_back, viewGroup, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BuyBackAdapter.ViewHolder, position: Int) {
        val buyBackList: BuyBackModel = arrayList[position]

        holder.txtTitle.text = buyBackList.titleLockUp
        holder.txtAmount.text = "$" + groupingSeparator(buyBackList.amountLockUp)
        holder.txtDateStart.text = buyBackList.dateStartLockUp
        holder.txtDateEnd.text = buyBackList.dateEndLockUp
        holder.txtDuration.text = buyBackList.durationLockUp
        holder.layoutBuyBack.setOnClickListener {
            onClickAdapter.onClickMe(buyBackList)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnClickAdapter{
        fun onClickMe(buyBackModel: BuyBackModel)
    }

    init {
        this.arrayList = arrayList
        this.onClickAdapter = onClickAdapter
    }
}
