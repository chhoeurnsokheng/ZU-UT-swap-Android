package com.zillennium.utswap.screens.project.projecrDetailScreen

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.screens.navbar.tradeTab.Trade

class ProjectDetailAdapter(arrayList: ArrayList<ProjectDetail>) :
    RecyclerView.Adapter<ProjectDetailAdapter.ViewHolder>() {
    private val listdata: ArrayList<ProjectDetail> = arrayList

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ivIcon: ImageView = view.findViewById<View>(R.id.iv_icon) as ImageView
        var linearLayout: LinearLayout = view.findViewById<View>(R.id.linear_layout) as LinearLayout
        var txtDesc: TextView = view.findViewById<View>(R.id.txt_desc) as TextView
        var txtTitle: TextView = view.findViewById<View>(R.id.txt_title) as TextView

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_project_article, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val projectDetail: ProjectDetail = listdata[i]
        viewHolder.ivIcon.setImageResource(projectDetail.icon)
        viewHolder.ivIcon.layoutParams.width = 0
        viewHolder.txtTitle.text = projectDetail.title
        if (projectDetail.title === "Google Map") {
            val desc: String = projectDetail.desc
            viewHolder.txtDesc.text = "Link Google Map"
            viewHolder.txtDesc.setTextColor(viewHolder.itemView.resources.getColor(R.color.primary))
            viewHolder.txtDesc.isClickable = true
            viewHolder.txtDesc.setOnClickListener { view: View ->
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(desc))
                view.context.startActivity(intent)
            }
        } else {
            viewHolder.txtDesc.text = projectDetail.desc
        }
        viewHolder.linearLayout.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

}