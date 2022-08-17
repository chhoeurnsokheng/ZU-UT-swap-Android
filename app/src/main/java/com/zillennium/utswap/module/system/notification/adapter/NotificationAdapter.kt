package com.zillennium.utswap.module.system.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListSystemNotificationBinding
import com.zillennium.utswap.models.notification.NotificationModel
import com.zillennium.utswap.utils.Util.Companion.dpToPx
import com.zillennium.utswap.utils.dpToPx

class NotificationAdapter() :
    BaseRecyclerViewAdapterGeneric<NotificationModel, NotificationAdapter.NotificationViewHolder>() {

    private var arrayList: ArrayList<NotificationModel> = ArrayList()

    inner class NotificationViewHolder(root: ItemListSystemNotificationBinding) :
        BaseViewHolder<ItemListSystemNotificationBinding>(root) {

        fun bindData(notificationModel: NotificationModel) {
            binding.apply {
                Glide.with(UTSwapApp.instance)
                    .asBitmap()
                    .load(notificationModel)
                    .placeholder(R.drawable.ic_notification)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(icNotification)
                txtTitle.text = notificationModel.txtTitle
                txtTitleAnnouncement.text = notificationModel.txtTitleAnnouncement
                txtDescription.text = notificationModel.txtDescription
                txtDuration.text = notificationModel.txtDuration


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
//                }
            }
        }

    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = NotificationViewHolder(ItemListSystemNotificationBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(holder: NotificationViewHolder, position: Int, context: Context) {
        holder.bindData(items[position])
    }

}
