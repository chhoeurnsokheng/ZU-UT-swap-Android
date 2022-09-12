package com.zillennium.utswap.module.project.projectScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListProjectBinding
import com.zillennium.utswap.models.project.ProjectList
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity

class ProjectAdapter(private var item: List<ProjectList.ProjectListData>) :
    RecyclerView.Adapter<ProjectAdapter.ProjectListViewHolder>() {

    inner class ProjectListViewHolder(root: ItemListProjectBinding) :
        BaseViewHolder<ItemListProjectBinding>(root) {
        fun bindData(projectList: ProjectList.ProjectListData) {
            binding.apply {
                Glide.with(UTSwapApp.instance)
                    .load(projectList.image)
                    .placeholder(com.zillennium.utswap.R.drawable.ic_placeholder)
                    .into(imageView)
                if (projectList.action == "Upcomming") {
                    txtStatus.visibility = View.VISIBLE
                } else {
                    txtStatus.visibility = View.GONE
                }
                titleProject.text = projectList.project_name
                subTitle.text = projectList.strategy

                linearCard.setOnClickListener {
                    ProjectInfoActivity.launchProjectInfoActivity(
                        root.context,
                        projectList.id,
//                        projectList.project_name
                    )
                }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectListViewHolder {
        return ProjectListViewHolder(
            ItemListProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProjectListViewHolder, position: Int) {
        holder.bindData(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }
}
