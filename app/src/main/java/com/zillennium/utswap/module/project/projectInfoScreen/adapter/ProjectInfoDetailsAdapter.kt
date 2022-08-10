package com.zillennium.utswap.module.project.projectInfoScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListProjectInfoDetailsBinding
import com.zillennium.utswap.models.ProjectInfoDetailModel
import com.zillennium.utswap.models.project.ProjectInfoDetail


class ProjectInfoDetailsAdapter() : BaseRecyclerViewAdapterGeneric<ProjectInfoDetailModel, ProjectInfoDetailsAdapter.ProjectDetailViewHolder>(){
    inner class ProjectDetailViewHolder(root: ItemListProjectInfoDetailsBinding) :
        BaseViewHolder<ItemListProjectInfoDetailsBinding>(root){
        fun bindData(projectInfoDetailData: ProjectInfoDetailModel) {
            binding.apply {
                titleInfo.text = projectInfoDetailData.titleInfo
                valueInfo.text = projectInfoDetailData.descriptionInfo.toString()

            }
        }

    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ProjectDetailViewHolder(ItemListProjectInfoDetailsBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: ProjectDetailViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bindData(items[position])
    }

}
