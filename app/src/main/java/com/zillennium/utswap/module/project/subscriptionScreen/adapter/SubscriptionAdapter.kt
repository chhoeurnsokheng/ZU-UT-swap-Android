package com.zillennium.utswap.module.project.subscriptionScreen.adapter

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class SubscriptionAdapter(var onclickAdapter: OnclickAdapter, var userLevel: String, var userLevelId: String) :
    BaseRecyclerViewAdapterGeneric<SubscriptionProject.SubscriptionProjectData, SubscriptionAdapter.SubscriptionViewHolder>() {
    inner class SubscriptionViewHolder(root: ItemListProjectSubscriptionBinding) :
        BaseViewHolder<ItemListProjectSubscriptionBinding>(root) {
        fun bindData(subscriptionList: SubscriptionProject.SubscriptionProjectData,position: Int) {
            binding.apply {

                //Marquee TextView
                tvTitle.isSelected = true

                val Dollar = subscriptionList.price.let { formatThreeDigitValue(it ?: 0, "###,###.##") }
                val UtValue = subscriptionList.deal?.let { groupingSeparatorInt(it.toInt()) }
                val UtMainValue = subscriptionList.num?.let { groupingSeparatorInt(it.toInt()) }
                tvProjectTitle.text = subscriptionList.name
                tvTitle.text = subscriptionList.user_account_type
                tvDollar.text = Dollar.toString()
                tvDayLock.text = subscriptionList.jian?.toInt().toString()
                tvUtValue.text = UtValue.toString()
                tvUtMainValue.text = UtMainValue.toString()
                txtEndTime.text = subscriptionList.endtime

                if (subscriptionList.content?.isNotEmpty() == true){
                    txtStatus.text = subscriptionList.content
                }else{
                    txtStatus.visibility = View.GONE
                    layoutDiscount.visibility =View.GONE
                }

                if (subscriptionList.status==1){
                    txtProjectStatus.text = "Processing"
                    txtProjectStatus.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                    layBookingPreSale.backgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.purple_700)
                }else if(subscriptionList.status==2){
                    txtProjectStatus.text = "Upcoming"
                    txtProjectStatus.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.dark_yellow))
                    layBookingPreSale.backgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.gray_999999)

                }else if (subscriptionList.status ==3){
                    txtProjectStatus.text = "Ended"
                    txtProjectStatus.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
                    layBookingPreSale.backgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.gray_999999)
                }

                if(userLevel.isEmpty()){

                    val totalUT = subscriptionList.num.toString().toInt()
                    val volumeUT = subscriptionList.deal.toString().toInt()
                    if (volumeUT == totalUT) {
                        imgCircle.imageTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.dark_pink)
                    }

                }else{
                    val userLevelConvert = userLevel.replace("\\s".toRegex(), "").substring(2)
                    if (subscriptionList.user_account_type.toString().contains(userLevelConvert)) {

                        if (subscriptionList.status==3){
                            CardViewPopup.isEnabled = false
                        }else if (subscriptionList.status ==2){
                            CardViewPopup.isEnabled = false
                        }
                        else{
                            CardViewPopup.isEnabled = true
                        }
                        imgCircle.imageTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.simple_green)
                        determinateBar.progressBackgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.gray_999999)
                    } else {
                        if(userLevelId == "5" || userLevelId == "4"){

                            imgCircle.imageTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.simple_green)
                            determinateBar.progressBackgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.gray_999999)
                            if (subscriptionList.status==3 ){
                                CardViewPopup.isEnabled = false
                            }else if (subscriptionList.status ==2){
                                CardViewPopup.isEnabled = false
                            }
                            else{
                                CardViewPopup.isEnabled = true
                            }

                        }else{
                            if (subscriptionList.status==3 || subscriptionList.status ==2){
                                CardViewPopup.isEnabled = false
                            }else{
                                CardViewPopup.isEnabled = true
                            }

                            imgCircle.imageTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.gray_E7E7E7)
                        }
                    }

                    val totalUT = subscriptionList.num.toString().toInt()
                    val volumeUT = subscriptionList.deal.toString().toInt()
                    if (volumeUT == totalUT) {
                        imgCircle.imageTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.dark_pink)
                    }
                    CardViewPopup.setOnClickListener {
                      //  itemView.isEnabled = false
                        if (!subscriptionList.user_account_type.toString().contains(userLevelConvert)) {
                            if(userLevelId == "5" || userLevelId == "4"){
                                if (volumeUT == totalUT) {
                                    itemView.isEnabled = false
                                    itemView.isClickable = false
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
                            }else{
                                Toast.makeText(UTSwapApp.instance, "Please upgrade your account", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            if (volumeUT == totalUT) {
                                itemView.isEnabled = false
                                itemView.isClickable = false
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

                        Handler().postDelayed({
                            itemView.isEnabled = true
                        }, 2000)
                    }
                }


                determinateBar.progress =
                    ((subscriptionList.deal?.toInt() ?: 0) * 100) / (subscriptionList.num?.toInt()
                        ?: 0)

                //Check enable and disable item with launch
                if (subscriptionList.homepage == 1) {
                    itemView.visibility = View.VISIBLE

                } else {
                    itemView.visibility = View.GONE
                    itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
                }

                //Check field pre-sale and booking
                if (subscriptionList.type == 1) {
                    layBookingPreSale.isEnabled = false
                    tvBookingPreSale.text = "Subscribe"


                } else {
                    layBookingPreSale.isEnabled = false
                    tvBookingPreSale.text = "Wait List"


                }

                if(position == items.size){
                    line.visibility = View.GONE
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
        holder.bindData(items[position],position)
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





