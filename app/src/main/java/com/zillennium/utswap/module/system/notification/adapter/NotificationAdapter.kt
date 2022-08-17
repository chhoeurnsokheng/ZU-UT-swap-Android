package com.zillennium.utswap.module.system.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.analytics.FirebaseAnalytics.Event.VIEW_ITEM
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListSystemNotificationBinding
import com.zillennium.utswap.databinding.ItemNewTitleBinding
import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.utils.dpToPx

class NotificationAdapter() :
    BaseRecyclerViewAdapterGeneric<NotificationModel.NotificationListData, NotificationAdapter.NotificationViewHolder>() {

    private var arrayList: ArrayList<NotificationModel.NotificationListData> = ArrayList()
    val VIEW_ITEM = 2
    val VIEW_TITLE = 1

    inner class NotificationViewHolder(root: ItemListSystemNotificationBinding) :
        BaseViewHolder<ItemListSystemNotificationBinding>(root) {

        fun bindData(notificationModel: NotificationModel.NotificationListData) {
            binding.apply {
                Glide.with(UTSwapApp.instance)
                    .asBitmap()
                    .load(notificationModel)
                    .placeholder(R.drawable.ic_notification)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(icNotification)
//                txtTitle.text = notificationModel.action_title
                txtTitleAnnouncement.text = notificationModel.action_title
                txtDescription.text = notificationModel.body
                txtDuration.text = notificationModel.sent_time


//                if (arrayList.size == 1) {
//                    line.visibility = View.GONE
//                } else {
//                    when (position) {
//                        arrayList.size - 1 -> {
//                            line.visibility = View.GONE
//                        }
//                        0 -> {
//                            line.visibility = View.VISIBLE
//                            linearContainer.setPadding(dpToPx(20), dpToPx(30), dpToPx(20), 0)
//                        }
//                        else -> {
//                            line.visibility = View.VISIBLE
//                        }
//                    }
            }
        }
    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): NotificationViewHolder {
        when (viewType) {
            VIEW_ITEM -> {
                return NotificationViewHolder(
                    ItemListSystemNotificationBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                )
            }
        }
        return NotificationViewHolder(
            ItemListSystemNotificationBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }
    override fun onBindItemHolder(holder: NotificationViewHolder, position: Int, context: Context) {
        holder.bindData(items[position])

    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)

    }

//    }
//}
//
//class NotificationViewHolder(root: ItemListSystemNotificationBinding) :
//    BaseViewHolder<ItemNewTitleBinding>(root) {
//
//    fun bindTitleData(notificationData: NotificationModel.NotificationListData) {
//        binding.apply {
//            txtTitle.text = notificationData.action_title
//
//        }
//    }

//    override fun onCreateItemHolder(
//        inflater: LayoutInflater,
//        parent: ViewGroup,
//        viewType: Int
//    ): NotificationAdapter.NotificationViewHolder {
//        when (viewType) {
//            VIEW_ITEM -> {
//                return NotificationViewHolder(
//                    ItemListSystemNotificationBinding.inflate(
//                        inflater,
//                        parent,
//                        false
//                    )
//                )
//            }
//
//            else -> {
//                return NotificationViewHolder(
//                    ItemNewTitleBinding.inflate(
//                        inflater,
//                        parent,
//                        false
//                    )
//                )
//            }
//        }
//    }



}
