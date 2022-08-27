package com.zillennium.utswap.module.project.subscriptionScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListProjectSubscriptionBinding
import com.zillennium.utswap.models.project.SubscriptionProject
import com.zillennium.utswap.utils.formatThreeDigitValue
import com.zillennium.utswap.utils.groupingSeparatorInt

class SubscriptionAdapter(var onclickAdapter: OnclickAdapter, var userLevel: String) :
    BaseRecyclerViewAdapterGeneric<SubscriptionProject.SubscriptionProjectData, SubscriptionAdapter.SubscriptionViewHolder>() {
    inner class SubscriptionViewHolder(root: ItemListProjectSubscriptionBinding) :
        BaseViewHolder<ItemListProjectSubscriptionBinding>(root) {
        fun bindData(subscriptionList: SubscriptionProject.SubscriptionProjectData) {
            binding.apply {
                val Dollar = subscriptionList.price.let { formatThreeDigitValue(it ?: 0, "###,###.##") }
                val UtValue = subscriptionList.deal?.let { groupingSeparatorInt(it.toInt()) }
                val UtMainValue = subscriptionList.num?.let { groupingSeparatorInt(it.toInt()) }
                tvTitle.text = subscriptionList.user_account_type
                tvDollar.text = Dollar.toString()
                tvDayLock.text = subscriptionList.jian.toString()
                tvUtValue.text = UtValue.toString()
                tvUtMainValue.text = UtMainValue.toString()

                if (userLevel.contains(subscriptionList.user_account_type.toString())) {
                    CardViewPopup.isEnabled = true
                    imgCircle.imageTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.simple_green)
                    determinateBar.progressBackgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.gray_999999)
                } else {
                    imgCircle.imageTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.gray_E7E7E7)
                    CardViewPopup.isEnabled = false
                }

                itemView.setOnClickListener {
                    if (Dollar != null) {
                        onclickAdapter.onClickMe(
                            subscriptionList.user_account_type.toString(),
                            subscriptionList.jian.toString(),
                            subscriptionList.id,
                            Dollar.toDouble()
                        )
                    }
                }
                determinateBar.progress =
                    ((subscriptionList.deal?.toInt() ?: 0) * 100) / (subscriptionList.num?.toInt()
                        ?: 0)
            }


        }

    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = SubscriptionViewHolder(
        ItemListProjectSubscriptionBinding.inflate(inflater, parent, false)
    )

    override fun onBindItemHolder(holder: SubscriptionViewHolder, position: Int, context: Context) {
        holder.bindData(items[position])
    }

    interface OnclickAdapter {
        fun onClickMe(title: String, lockTime: String, subscriptionId: Int, volumePrice: Double)
    }

}





