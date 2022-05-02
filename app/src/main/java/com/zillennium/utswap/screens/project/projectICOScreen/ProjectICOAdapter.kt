package com.zillennium.utswap.screens.project.projectICOScreen

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

class ProjectICOAdapter(private val listdata: ArrayList<ProjectICO>) :
    RecyclerView.Adapter<ProjectICOAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ivIcon = view.findViewById<View>(R.id.iv_icon) as ImageView
        var txtTitle = view.findViewById<View>(R.id.txt_title) as TextView
        var txtDesc = view.findViewById<View>(R.id.txt_desc) as TextView
        var linearLayout = view.findViewById<View>(R.id.linear_layout) as LinearLayout

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_project_article, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val projectICO = listdata[i]
        viewHolder.ivIcon.setImageResource(projectICO.icon)
        viewHolder.txtTitle.text = projectICO.title
        if (projectICO.title === "Google Map") {
            val desc = projectICO.desc
            viewHolder.txtDesc.text = "Link Google Map"
            viewHolder.txtDesc.setTextColor(viewHolder.itemView.resources.getColor(R.color.primary))
            viewHolder.txtDesc.isClickable = true
            viewHolder.txtDesc.setOnClickListener { view: View ->
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(desc))
                view.context.startActivity(intent)
            }
        } else {
            viewHolder.txtDesc.text = projectICO.desc
        }
        viewHolder.linearLayout.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return listdata.size
    }
}