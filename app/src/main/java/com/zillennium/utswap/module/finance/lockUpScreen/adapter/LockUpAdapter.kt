package com.zillennium.utswap.module.finance.lockUpScreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.FinanceLockUpModel
import com.zillennium.utswap.models.lockUpBalance.LockUpBalanceObject
import com.zillennium.utswap.utils.groupingSeparator

class LockUpAdapter(var arrayList: ArrayList<Any>, var onClickAdapter: OnClickAdapter)
    :RecyclerView.Adapter<LockUpAdapter.ViewHolder>(){
    private var isLock =  false

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        internal val txtTitle: TextView = view.findViewById<View>(R.id.txt_title) as TextView
        internal val txtAmount: TextView = view.findViewById<View>(R.id.txt_amount) as TextView
        internal val txtDateStart: TextView = view.findViewById<View>(R.id.txt_date_start) as TextView
        internal val txtDateEnd: TextView = view.findViewById<View>(R.id.txt_date_end) as TextView
        internal val txtDuration: TextView = view.findViewById<View>(R.id.txt_duration) as TextView
        internal val layoutBuyBack: LinearLayout = view.findViewById<View>(R.id.linear_buy_back) as LinearLayout
        internal val imageBalance: ImageView = view.findViewById(R.id.image_balance)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LockUpAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_finance_lock_up, viewGroup, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LockUpAdapter.ViewHolder, position: Int) {
        val buyBackList: LockUpBalanceObject.LockUpBalanceItem = arrayList[position] as LockUpBalanceObject.LockUpBalanceItem

        holder.txtTitle.text = buyBackList.name
        holder.txtAmount.text = "$" + groupingSeparator(buyBackList.amount)
        holder.txtDateStart.text = buyBackList.addtimeReable
        holder.txtDateEnd.text = buyBackList.endtimeReable
        holder.txtDuration.text = "${buyBackList.lock_period} Day(s) left"
        if (buyBackList.unlock_by != null) {
            isLock = false
            holder.imageBalance.setImageResource(R.drawable.ic_unlocked)
        } else {
            isLock = true
            holder.imageBalance.setImageResource(R.drawable.ic_locked)
        }
        holder.layoutBuyBack.setOnClickListener {
            onClickAdapter.onClickMe(buyBackList.name, buyBackList.amount, buyBackList.addtimeReable,buyBackList.endtimeReable, buyBackList.lock_period,isLock)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnClickAdapter{
        fun onClickMe(projectName: String, amount: Double, start: String, end: String, duration: Int, isUnLock: Boolean)
    }


}
