package com.zillennium.utswap.module.account.accountDetailScreen.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListAccountUtTypePriorityBinding
import com.zillennium.utswap.models.userService.User

class AccountUTTypePriorityAdapter : BaseRecyclerViewAdapterGeneric<User.PriorityAndPrivilegesList, AccountUTTypePriorityAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(root: ItemListAccountUtTypePriorityBinding): BaseViewHolder<ItemListAccountUtTypePriorityBinding>(root) {
        @SuppressLint("SetTextI18n")
        fun bindData(priorityAndPrivilegesList: User.PriorityAndPrivilegesList, position: Int) {
            binding.apply {
                tvId.text = priorityAndPrivilegesList.id.toString() + "."
                tvContent.text = priorityAndPrivilegesList.content.toString()
                tvTitle.text = priorityAndPrivilegesList.title.toString()
            }
        }
    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    )= ItemViewHolder(ItemListAccountUtTypePriorityBinding.inflate(inflater,parent,false))

    override fun onBindItemHolder(holder: ItemViewHolder, position: Int, context: Context) {
        holder.bindData(items[position],position)
    }
}