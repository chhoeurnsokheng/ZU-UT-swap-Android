package com.zillennium.utswap.module.finance.withdrawScreen.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.ItemListFinanceDepositPaymentBinding
import com.zillennium.utswap.models.withdraw.WithdrawObj

class WithdrawAdapter(val item: List<WithdrawObj.DataWithdraw>, val onClickBank: OnClickBank): RecyclerView.Adapter<WithdrawAdapter.WithdrawViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WithdrawViewHolder {
        return WithdrawViewHolder(
            return WithdrawViewHolder(
                ItemListFinanceDepositPaymentBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        )
    }

    override fun onBindViewHolder(holder: WithdrawViewHolder, position: Int) {
        holder.bind(item[position])

    }

    override fun getItemCount(): Int {
        return  item.size
    }

    interface OnClickBank {
        fun clickWithdrawBank(titleBank: String?, imgBank: String?)
    }

   inner class WithdrawViewHolder(var binding:ItemListFinanceDepositPaymentBinding):RecyclerView.ViewHolder(binding.root) {
         fun bind(data: WithdrawObj.DataWithdraw){
             binding.apply {
                 Glide.with(imgCardImage).load(data.url).fitCenter().into(imgCardImage)
                 tvCardName.text = data.name
                 linearLayout.setOnClickListener {
                     if (linearLayout.isSelected == true){
                         linearLayout.setBackgroundColor(ContextCompat.getColor(root.context, R.color.gray_EDEDED))
                     }
                     onClickBank.clickWithdrawBank(data.name, data.url)
                 }
             }
         }
    }


}