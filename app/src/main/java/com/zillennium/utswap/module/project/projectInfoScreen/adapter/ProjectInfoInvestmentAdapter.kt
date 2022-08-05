package com.zillennium.utswap.module.project.projectInfoScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListProjectInfoInvestmentBinding
import com.zillennium.utswap.models.projectList.ProjectInfoDetail

class ProjectInfoInvestmentAdapter : BaseRecyclerViewAdapterGeneric <ProjectInfoDetail.ProjectInfoDetailData, ProjectInfoInvestmentAdapter.ProjectInfoInvestmentViewHolder>(){
    inner class ProjectInfoInvestmentViewHolder(root: ItemListProjectInfoInvestmentBinding) : BaseViewHolder<ItemListProjectInfoInvestmentBinding>(root) {
        fun bindData(projectInfoDetailData: ProjectInfoDetail.ProjectInfoDetailData) {
binding.apply {


        if(position == 0)
        {
//            layoutInvestment.setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, R.color.gray_CCCCCC))
        }
        else if(position == 1){
//           layoutInvestment.setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, R.color.warning))
        }
}
        }

    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ProjectInfoInvestmentViewHolder(ItemListProjectInfoInvestmentBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: ProjectInfoInvestmentViewHolder,
        position: Int,
        context: Context
    ) {
       holder.bindData(items[position])
    }
}
