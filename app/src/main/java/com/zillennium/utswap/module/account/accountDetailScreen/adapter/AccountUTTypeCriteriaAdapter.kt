package com.zillennium.utswap.module.account.accountDetailScreen.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListAccountUtTypeCriteriaBinding
import com.zillennium.utswap.models.userService.User

class AccountUTTypeCriteriaAdapter : BaseRecyclerViewAdapterGeneric<User.CriteriaList, AccountUTTypeCriteriaAdapter.ItemViewHolder>(){

    inner class ItemViewHolder(root: ItemListAccountUtTypeCriteriaBinding): BaseViewHolder<ItemListAccountUtTypeCriteriaBinding>(root) {
        @SuppressLint("SetTextI18n")
        fun bindData(criteriaList: User.CriteriaList, position: Int) {
            binding.apply {
                tvId.text = criteriaList.id.toString() + "."
                tvContent.text = Html.fromHtml(criteriaList.content.toString())
                tvTitle.text = criteriaList.title.toString()
            }
        }
    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    )= ItemViewHolder(ItemListAccountUtTypeCriteriaBinding.inflate(inflater,parent,false))

    override fun onBindItemHolder(holder: ItemViewHolder, position: Int, context: Context) {
        holder.bindData(items[position],position)
    }
}