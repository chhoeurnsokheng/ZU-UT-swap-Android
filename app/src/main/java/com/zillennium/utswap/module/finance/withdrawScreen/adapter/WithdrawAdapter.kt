package com.zillennium.utswap.module.finance.withdrawScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.WithdrawAddbankModel
import com.zillennium.utswap.utils.dpToPx

class WithdrawAdapter(private val arrayList: ArrayList<WithdrawAddbankModel>, onClickBank: OnClickBank): RecyclerView.Adapter<WithdrawAdapter.ViewHolder>() {

    private val listData: ArrayList<WithdrawAddbankModel> = arrayList
    private var onclickWithdrawBank: OnClickBank

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var layWithdrawAddbankItem: LinearLayout = view.findViewById(R.id.layDepositItem) as LinearLayout
        var bank_image: ImageView = view.findViewById(R.id.img_cardImage) as ImageView
        var bank_Title: TextView = view.findViewById(R.id.tv_card_name) as TextView
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WithdrawAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_finance_deposit_payment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WithdrawAdapter.ViewHolder, position: Int) {
        val withdrawCurrentItemList: WithdrawAddbankModel = listData[position]
        holder.bank_image.setImageResource(withdrawCurrentItemList.bankImg)
        holder.bank_Title.text = withdrawCurrentItemList.bankTitle

        when(withdrawCurrentItemList.bankTitle){
            "ABA Pay" ->{
                holder.bank_image.layoutParams.width = dpToPx(30)
                holder.bank_Title.layoutParams.height = dpToPx(18)
            }
            "Acleda Bank" ->{
                holder.bank_image.layoutParams.width = dpToPx(29)
                holder.bank_Title.layoutParams.height = dpToPx(29)
            }
            "Sathapana" ->{
                holder.bank_image.layoutParams.width = dpToPx(27)
                holder.bank_Title.layoutParams.height = dpToPx(32)
            }
        }
        holder.layWithdrawAddbankItem.setOnClickListener { onclickWithdrawBank.clickWithdrawBank(withdrawCurrentItemList.bankTitle, withdrawCurrentItemList.bankImg) }


    }

    override fun getItemCount(): Int {
        return listData.size
    }

    interface OnClickBank {
        fun clickWithdrawBank(titleBank: String, imgBank: Int)
    }

    init {
        this.onclickWithdrawBank = onClickBank
    }
}