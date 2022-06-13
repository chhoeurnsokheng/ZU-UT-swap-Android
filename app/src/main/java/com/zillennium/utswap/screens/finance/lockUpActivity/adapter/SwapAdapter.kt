package com.zillennium.utswap.screens.finance.lockUpActivity.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.financeLockUp.SwapModel
import com.zillennium.utswap.utils.groupingSeparator
import kotlin.math.roundToInt

class SwapAdapter(arrayList: ArrayList<SwapModel>, onClickAdapter: OnClickAdapter)
    :RecyclerView.Adapter<SwapAdapter.ViewHolder>(){

    private var arrayList: ArrayList<SwapModel> = arrayList
    private var onClickAdapter: OnClickAdapter

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        internal val txtTitle: TextView = view.findViewById<View>(R.id.txt_title) as TextView
        internal val txtAmount: TextView = view.findViewById<View>(R.id.txt_amount) as TextView
        internal val txtDateStart: TextView = view.findViewById<View>(R.id.txt_date_start) as TextView
        internal val txtDateEnd: TextView = view.findViewById<View>(R.id.txt_date_end) as TextView
        internal val txtDuration: TextView = view.findViewById<View>(R.id.txt_duration) as TextView
        internal val layoutBuyBack: LinearLayout = view.findViewById<View>(R.id.linear_swap) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SwapAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_finance_lock_up_swap, viewGroup, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SwapAdapter.ViewHolder, position: Int) {
        val swapList: SwapModel = arrayList[position]

        holder.txtTitle.text = swapList.titleLockUp
        holder.txtAmount.text = "$" + groupingSeparator(swapList.amountLockUp)
        holder.txtDateStart.text = swapList.dateStartLockUp
        holder.txtDateEnd.text = swapList.dateEndLockUp
        holder.txtDuration.text = swapList.durationLockUp

        holder.layoutBuyBack.setOnClickListener {
            onClickAdapter.onClickMe(swapList)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnClickAdapter{
        fun onClickMe(buyBackModel: SwapModel)
    }

    init {
        this.onClickAdapter = onClickAdapter
    }
}