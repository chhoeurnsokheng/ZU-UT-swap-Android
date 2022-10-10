package com.zillennium.utswap.module.finance.subscriptionScreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.ItemListFinanceSubscriptionsBinding
import com.zillennium.utswap.models.financeSubscription.SubscriptionObject
import com.zillennium.utswap.utils.groupingSeparator
import rx.internal.util.SubscriptionList

class FinanceSubscriptionsAdapter(
    var arrayList: ArrayList<SubscriptionObject.SubscriptionList>,
    var onClickAdapter: OnClickAdapter
) :
    RecyclerView.Adapter<FinanceSubscriptionsAdapter.ViewHolder>() {

    private var isLocked = false

    inner class ViewHolder(var binding: ItemListFinanceSubscriptionsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SubscriptionObject.SubscriptionList) {
            binding.apply {

                titleSubscription.text = data.name
                dateSubscription.text = data.addtimeReadble
                amountSubscription.text = "$ " + groupingSeparator(data.mum.toDouble())

                if (data.status == "Completed") {
                    imageSubscription.setImageResource(R.drawable.ic_locked)
                    statusDays.text = "${(data.lock_period_left)} day(s) left"
                } else {
                    imageSubscription.setImageResource(R.drawable.ic_unlocked)
                    statusDays.text = "—"
                }
                linearSubscription.setOnClickListener {
                    isLocked = data.status == "Completed"
                    onClickAdapter.onSubscriptionItemClick(
                        data.name,
                        isLocked,
                        data.transaction_id,
                        data.price,
                        data.num,
                        data.mum.toDouble(),
                        data.startDate,
                        data.endDate,
                        data.lock_period_left
                    )


                }

            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListFinanceSubscriptionsBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )


    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnClickAdapter {
        fun onSubscriptionItemClick(
            title: String,
            isLock: Boolean,
            tranId: String,
            price: Double,
            volume: String,
            value: Double,
            start: String,
            end: String,
            duration: Int
        )
    }
}


