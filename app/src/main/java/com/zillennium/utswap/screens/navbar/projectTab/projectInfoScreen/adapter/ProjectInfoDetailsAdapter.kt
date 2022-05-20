package com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.ProjectInfoDetail

class ProjectInfoDetailsAdapter(arrayList: ArrayList<ProjectInfoDetail>) :
    RecyclerView.Adapter<ProjectInfoDetailsAdapter.ViewHolder>() {
    private val listdata: ArrayList<ProjectInfoDetail> = arrayList

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitleInfo: TextView = view.findViewById<View>(R.id.title_info) as TextView
        var txtDescriptionInfo: TextView = view.findViewById<View>(R.id.value_info) as TextView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_project_info_details, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val projectInfoDetailList: ProjectInfoDetail = listdata[position]
        holder.txtTitleInfo.text = projectInfoDetailList.titleInfo
        holder.txtDescriptionInfo.text = projectInfoDetailList.descriptionInfo

    }

    override fun getItemCount(): Int {
        return listdata.size
    }

}