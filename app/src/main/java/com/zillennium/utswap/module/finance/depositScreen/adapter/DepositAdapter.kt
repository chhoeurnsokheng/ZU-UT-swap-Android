package com.zillennium.utswap.module.finance.depositScreen.adapter

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.ItemListFinanceBalanceBinding
import com.zillennium.utswap.databinding.ItemListFinanceDepositPaymentBinding
import com.zillennium.utswap.models.DepositModel
import com.zillennium.utswap.models.deposite.DepositObj
import com.zillennium.utswap.module.finance.depositScreen.depositBottomSheet.BottomSheetFinanceDepositPayment
import com.zillennium.utswap.utils.dpToPx


class DepositAdapter( val item: List<DepositObj.DataListRes>, val onClickListener: OnClickDeposit) : RecyclerView.Adapter<DepositAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListFinanceDepositPaymentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(item[position])
    }
  inner  class ViewHolder(var binding:ItemListFinanceDepositPaymentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DepositObj.DataListRes){
            binding.apply {
                Glide.with(imgCardImage).load(data.img_url).fitCenter().into(imgCardImage)
                tvCardName.text = data.title
                linearLayout.setOnClickListener {

                    if (linearLayout.isSelected == true){
                        linearLayout.setBackgroundColor(ContextCompat.getColor(root.context,R.color.gray_EDEDED))
                    }
                  onClickListener.ClickDepositCard(data.title, data.img_url,data.bic)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    interface OnClickDeposit {
        fun ClickDepositCard(cardTitle: String?,cardImg: String?,bic:String?)
    }
}
