package com.zillennium.utswap.module.project.projectScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListProjectGridBinding
import com.zillennium.utswap.models.project.ProjectList

class ProjectGridAdapter(private var onclickGridProject: OnClickGridProject) :
    BaseRecyclerViewAdapterGeneric<ProjectList.ProjectListData, ProjectGridAdapter.ProjectListViewHolder>() {

    inner class ProjectListViewHolder(root: ItemListProjectGridBinding) :
        BaseViewHolder<ItemListProjectGridBinding>(root) {
        fun bindData(projectList: ProjectList.ProjectListData) {
            binding.apply {
                Glide.with(UTSwapApp.instance)
                    .load(projectList.image)
                    .into(imageView)
                titleProject.text = projectList.project_name
                subTitle.text = projectList.action

                //On Click Move to Detail Project
                linearCard.setOnClickListener{
                    onclickGridProject.onClickMe(projectList.id.toString())
                }
            }
        }

    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ProjectListViewHolder(ItemListProjectGridBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(holder: ProjectListViewHolder, position: Int, context: Context) {
        holder.bindData(items[position])
    }

    interface OnClickGridProject {
        fun onClickMe(id: String)
    }
}
