package com.zillennium.utswap.module.system.notification.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.databinding.ItemEarlierTitleBinding
import com.zillennium.utswap.databinding.ItemListSystemNotificationBinding
import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.utils.dpToPx
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotificationAdapter(var onClickNotificationAdapter: OnClickNotificationAdapter) :
    BaseRecyclerViewAdapterGeneric<NotificationModel.NotificationListData, NotificationAdapter.NotificationViewHolder>() {

    private var arrayList: ArrayList<NotificationModel.NotificationListData> = ArrayList()
    private lateinit var mBinding: ViewDataBinding
    val VIEW_ITEM = 2
    val VIEW_TITLE = 1


    inner class NotificationViewHolder(var rootView: ViewDataBinding) :
        RecyclerView.ViewHolder(rootView.root) {

        fun bindItem(notificationModel: NotificationModel.NotificationListData, position: Int) {
            rootView.apply {
                this as ItemListSystemNotificationBinding
                Glide.with(UTSwapApp.instance)
                    .asBitmap()
                    .load(notificationModel.icon)
                    .placeholder(R.drawable.ic_notification)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(icNotification)
                txtTitleAnnouncement.text = notificationModel.action_type
                txtDescription.text = notificationModel.body
                txtDuration.text = notificationModel.sent_time

                var date = "Mar 10, 2016 6:30:00 PM"
                var spf = SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa")
                val newDate: Date = spf.parse(date)
                spf = SimpleDateFormat("dd MMM yyyy")
                date = spf.format(newDate)


                Log.d( "date", date)

                if (position == items.lastIndex) {
                    layoutNotification.setPadding(dpToPx(20), dpToPx(20), dpToPx(20), dpToPx(20))
                }

                if (notificationModel.mark_as_read_msg == "1") {
                    layoutNotification.setBackgroundColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.white
                        )
                    )
                } else {
                    layoutNotification.setBackgroundColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.notification_unread
                        )
                    )
                }

                itemView.setOnClickListener {
                    onClickNotificationAdapter.clickNotification(notificationModel.notifi_type, notificationModel.id.toString())
                    layoutNotification.setBackgroundColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.white
                        )
                    )
                }
            }


        }

        fun bindTitle(notificationModel: NotificationModel.NotificationListData) {
            rootView.apply {
                this as ItemEarlierTitleBinding
                txtTitle.text = notificationModel.title
            }
        }

    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): NotificationViewHolder {
        mBinding = when (viewType) {
            VIEW_ITEM -> {
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_list_system_notification,
                    null,
                    false
                )
            }
            else -> {
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_earlier_title,
                    null,
                    false
                )
            }
        }
        return NotificationViewHolder(mBinding)

    }

    override fun onBindItemHolder(holder: NotificationViewHolder, position: Int, context: Context) {
        if (getItemViewType(position) == VIEW_ITEM) {
            holder.bindItem(items[position], position)
        } else {
            holder.bindTitle(items[position])
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        if (item.title?.isNotEmpty() == true) {
            return VIEW_TITLE
        }
        return VIEW_ITEM

    }

    interface OnClickNotificationAdapter {
        fun clickNotification(notify_type: String, idNotifi: String)
    }


}
