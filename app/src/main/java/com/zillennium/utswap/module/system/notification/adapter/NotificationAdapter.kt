package com.zillennium.utswap.module.system.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
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

class NotificationAdapter() :
    BaseRecyclerViewAdapterGeneric<NotificationModel.NotificationListData, NotificationAdapter.NotificationViewHolder>() {

    private var arrayList: ArrayList<NotificationModel.NotificationListData> = ArrayList()
    private lateinit var mBinding: ViewDataBinding
    val VIEW_ITEM = 2
    val VIEW_TITLE = 1


    inner class NotificationViewHolder(var rootView: ViewDataBinding) :
        RecyclerView.ViewHolder(rootView.root) {

        fun bindItem(notificationModel: NotificationModel.NotificationListData) {
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
//                txtTitle.text = notificationModel.action_title
                txtTitleAnnouncement.text = notificationModel.action_title
                txtDescription.text = notificationModel.body
                txtDuration.text = notificationModel.sent_time
            }
        }

        fun bindTitle(notificationModel: NotificationModel.NotificationListData) {
            rootView.apply {
                this as ItemEarlierTitleBinding
                txtTitle.text = notificationModel.title
            }
        }



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
            holder.bindItem(items[position])
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
