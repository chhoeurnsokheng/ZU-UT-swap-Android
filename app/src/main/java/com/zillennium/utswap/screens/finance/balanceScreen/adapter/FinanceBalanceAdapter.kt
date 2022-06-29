package com.zillennium.utswap.screens.finance.balanceScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.FinanceBalanceModel

class FinanceBalanceAdapter (arrayList: ArrayList<FinanceBalanceModel>, onClickAdapter: OnClickAdapter):
    RecyclerView.Adapter<FinanceBalanceAdapter.ViewHolder>() {

    private var listData: ArrayList<FinanceBalanceModel> = arrayList
    private val onClickAdapter: OnClickAdapter

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageImageView: ImageView = view.findViewById<View>(R.id.image_balance) as ImageView
        var titleTransaction: TextView = view.findViewById<View>(R.id.title_transaction) as TextView
        var dateTransaction: TextView = view.findViewById<View>(R.id.date_transaction) as TextView
        var amountBalance: TextView = view.findViewById<View>(R.id.amount_balance) as TextView
        var layoutRecentNewsCard: LinearLayout = view.findViewById<View>(R.id.linear_balance) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_finance_balance, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val financeBalanceList: FinanceBalanceModel = listData[position]
        holder.imageImageView.setImageResource(financeBalanceList.imageBalance)
        holder.titleTransaction.text = financeBalanceList.titleTransaction
        holder.dateTransaction.text = financeBalanceList.dateTransaction
        if(financeBalanceList.amountBalance < 0){
            val length = financeBalanceList.amountBalance.toString().length
            if ((financeBalanceList.amountBalance.toString()[0] == '-')) {
                if (length > 1) {
                    holder.amountBalance.text = "-$" + financeBalanceList.amountBalance.toString().substring(1, length)
                }
            }
            holder.amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
        }else {
            holder.amountBalance.text = "$" + financeBalanceList.amountBalance
            holder.amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
        }
        holder.layoutRecentNewsCard.setOnClickListener {
            onClickAdapter.onClickMe(financeBalanceList)
        }
    }

    override fun getItemCount(): Int {
        val limit = 10
        return Math.min(listData.size, limit)
    }

    interface OnClickAdapter{
        fun onClickMe(financeBalanceModel: FinanceBalanceModel)
    }

    init {
        this.listData = arrayList
        this.onClickAdapter = onClickAdapter
    }
}
