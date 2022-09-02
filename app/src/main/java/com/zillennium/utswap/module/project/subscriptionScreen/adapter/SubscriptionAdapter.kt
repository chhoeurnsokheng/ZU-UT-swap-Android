package com.zillennium.utswap.module.project.subscriptionScreen.adapter

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.unit.TextUnit
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
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
                val Dollar =
                    subscriptionList.price.let { formatThreeDigitValue(it ?: 0, "###,###.##") }
                val UtValue = subscriptionList.deal?.let { groupingSeparatorInt(it.toInt()) }
                val UtMainValue = subscriptionList.num?.let { groupingSeparatorInt(it.toInt()) }
                tvProjectTitle.text = subscriptionList.name
                tvTitle.text = subscriptionList.user_account_type
                tvDollar.text = Dollar.toString()
                tvDayLock.text = subscriptionList.jian.toString()
                tvUtValue.text = UtValue.toString()
                tvUtMainValue.text = UtMainValue.toString()
                txtEndTime.text = subscriptionList.endtime

                //Marquee TextView
                tvTitle.isSingleLine = true
                tvTitle.isSelected = true
                tvTitle.setHorizontallyScrolling(true)
                tvTitle.marqueeRepeatLimit = -1



                val userLevelConvert = userLevel.replace("\\s".toRegex(), "").substring(2)
                if (subscriptionList.user_account_type.toString().contains(userLevelConvert)) {
                    CardViewPopup.isEnabled = true
                    imgCircle.imageTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.simple_green)
                    determinateBar.progressBackgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.gray_999999)
                } else {
                    imgCircle.imageTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.gray_E7E7E7)
                }

                val totalUT = subscriptionList.num.toString().toInt()
                val volumeUT = subscriptionList.deal.toString().toInt()
                if (volumeUT == totalUT) {
                    imgCircle.imageTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.dark_pink)
                }
                itemView.setOnClickListener {
                    if (!subscriptionList.user_account_type.toString().contains(userLevelConvert)) {
                        Toast.makeText(UTSwapApp.instance, "please upgrade your account", Toast.LENGTH_SHORT).show()
                    } else {
                        if (volumeUT == totalUT) {
                            itemView.isEnabled = false
                            itemView.isClickable = false
//                            Toast.makeText(UTSwapApp.instance, "The current ICO is over", Toast.LENGTH_SHORT).show()
                        } else {
                            if (Dollar != null) {
                                onclickAdapter.onClickMe(
                                    subscriptionList.user_account_type.toString(),
                                    subscriptionList.jian.toString(),
                                    subscriptionList.id,
                                    Dollar.toDouble(),
                                    subscriptionList.num.toString().toInt(),
                                    subscriptionList.min.toString().toInt(),
                                    subscriptionList.max.toString().toInt()

                                )
                            }
                        }
                    }
                }
                determinateBar.progress =
                    ((subscriptionList.deal?.toInt() ?: 0) * 100) / (subscriptionList.num?.toInt()
                        ?: 0)

                //Check enable and disable item with launch
                if (subscriptionList.launch == 1) {
                    itemView.visibility = View.VISIBLE

                } else {
                    itemView.visibility = View.GONE
                    itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
                }

                //Check field pre-sale and booking
                if (subscriptionList.type == 1) {
                    layBookingPreSale.isEnabled = false
                    tvBookingPreSale.text = "Pre-Sale"
                    layBookingPreSale.backgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.purple_700)

                } else {
                    layBookingPreSale.isEnabled = false
                    tvBookingPreSale.text = "Booking"
                    layBookingPreSale.backgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.gray_999999)

                }
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
        fun onClickMe(
            title: String,
            lockTime: String,
            subscriptionId: Int,
            volumePrice: Double,
            totalUt: Int,
            min: Int,
            max: Int
        )
    }

}





