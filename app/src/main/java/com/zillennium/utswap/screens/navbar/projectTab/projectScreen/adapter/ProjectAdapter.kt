package com.zillennium.utswap.screens.navbar.projectTab.projectScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.ProjectModel

class ProjectAdapter(
    private val arrayList: ArrayList<ProjectModel>,
    private val itemListProject: Int,
    private val onclickProject: OnclickProject) :
    RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var linearCard: LinearLayout = view.findViewById(R.id.linear_card) as LinearLayout
        var publicDate: TextView = view.findViewById<View>(R.id.public_date) as TextView
        var imageView: ImageView = view.findViewById<View>(R.id.image_view) as ImageView
        var txttitle: TextView = view.findViewById<View>(R.id.title_project) as TextView
        var txtsubTitle: TextView = view.findViewById<View>(R.id.sub_title) as TextView
        var txtstatus: TextView = view.findViewById<View>(R.id.txt_status) as TextView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(itemListProject, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val projectList: ProjectModel = arrayList[position]
        holder.publicDate.text = projectList.publicDate
        holder.imageView.setImageResource(projectList.imageIcon)
        if (projectList.status.isNotEmpty()) {
            holder.txtstatus.isVisible = true
            holder.txttitle.text = projectList.titleProject
        }
        holder.txtsubTitle.text = projectList.subTitle
        holder.txtstatus.text = projectList.status
        holder.linearCard.setOnClickListener {
            onclickProject.onClickMe(
                projectList,
                projectList.id
            )

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnclickProject {
        fun onClickMe(projectHistory: ProjectModel?, selectedPosition: Int?)
    }

}
