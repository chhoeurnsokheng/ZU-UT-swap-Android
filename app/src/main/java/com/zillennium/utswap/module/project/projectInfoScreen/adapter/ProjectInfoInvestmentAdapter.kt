package com.zillennium.utswap.module.project.projectInfoScreen.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListProjectInfoInvestmentBinding
import com.zillennium.utswap.models.ProjectInfoInvestmentModel
import com.zillennium.utswap.models.project.ProjectInfoDetail

class ProjectInfoInvestmentAdapter : BaseRecyclerViewAdapterGeneric<ProjectInfoDetail.ProjectInfoDetailData, ProjectInfoInvestmentAdapter.ProjectInfoInvestmentViewHolder>(){
    inner class ProjectInfoInvestmentViewHolder(root: ItemListProjectInfoInvestmentBinding) : BaseViewHolder<ItemListProjectInfoInvestmentBinding>(root){
        fun bindData(projectInfoDetailData: ProjectInfoDetail.ProjectInfoDetailData) {
            binding.apply {
                txtPerUt.text = projectInfoDetailData.total_ut.toString()
                txtValueUt.text = projectInfoDetailData.total_ut.toString()
                txtSqmUt.text = projectInfoDetailData.title_deed
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
