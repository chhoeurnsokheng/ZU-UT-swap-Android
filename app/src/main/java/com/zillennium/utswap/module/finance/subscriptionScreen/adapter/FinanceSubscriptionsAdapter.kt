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
                amountSubscription.text = "$" + groupingSeparator(data.mum.toDouble())

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

//        var imageSubscription: ImageView =
//            view.findViewById<View>(R.id.image_subscription) as ImageView
//        var titleSubscriptions: TextView =
//            view.findViewById<View>(R.id.title_subscription) as TextView
//        var dateSubscription: TextView = view.findViewById<View>(R.id.date_subscription) as TextView
//        var amountSubscription: TextView =
//            view.findViewById<View>(R.id.amount_subscription) as TextView
//        var statusDays: TextView = view.findViewById(R.id.status_days) as TextView
//        var layoutSubscription: LinearLayout =
//            view.findViewById<View>(R.id.linear_subscription) as LinearLayout
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

//        val itemSubscriptionList = arrayList[position]
//        holder.titleSubscriptions.text = itemSubscriptionList.name
//        holder.dateSubscription.text = itemSubscriptionList.addtimeReadble
//        holder.amountSubscription.text = "$" + groupingSeparator(itemSubscriptionList.mum.toDouble())
//        if (itemSubscriptionList.status == "Completed") {
//
//            holder.imageSubscription.setImageResource(R.drawable.ic_locked)
//            holder.statusDays.text = "${  (itemSubscriptionList.lock_period_left)} day(s) left"
//        } else {
//
//            holder.imageSubscription.setImageResource(R.drawable.ic_unlocked)
//            holder.statusDays.text = "—"
//        }
//
//        holder.layoutSubscription.setOnClickListener {
//            isLocked = itemSubscriptionList.status =="Completed"
//
//            onClickAdapter.onSubscriptionItemClick(
//                itemSubscriptionList.name,
//                isLocked,
//                itemSubscriptionList.transaction_id,
//                itemSubscriptionList.price,
//                itemSubscriptionList.num,
//                itemSubscriptionList.mum.toDouble(),
//                itemSubscriptionList.startDate,
//                itemSubscriptionList.endDate,
//                itemSubscriptionList.lock_period_left
//            )
//        }
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


