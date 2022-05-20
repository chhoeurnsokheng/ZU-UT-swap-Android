package com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.ProjectInfoInvestment

class ProjectInfoInvestmentAdapter (arrayList: ArrayList<ProjectInfoInvestment>) :
    RecyclerView.Adapter<ProjectInfoInvestmentAdapter.ViewHolder>() {
    private val listdata:  ArrayList<ProjectInfoInvestment> = arrayList

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var txtPerUT: TextView = view.findViewById<View>(R.id.txt_per_ut) as TextView
        var txtValueUT: TextView = view.findViewById<View>(R.id.txt_value_ut) as TextView
        var txtSqmUT: TextView = view.findViewById<View>(R.id.txt_sqm_ut) as TextView
        var layoutInvestment: LinearLayout = view.findViewById(R.id.layout_investment) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_project_info_investment, viewGroup, false)
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val projectInfoInvestmentList: ProjectInfoInvestment = listdata[position]

        holder.txtPerUT.text = projectInfoInvestmentList.perUT
        holder.txtValueUT.text = projectInfoInvestmentList.valueUT
        holder.txtSqmUT.text = projectInfoInvestmentList.sqmUT

        if(position == 2)
        {
            holder.layoutInvestment.setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, R.color.thirst_text))
        }
        else if(position == 8){
            holder.layoutInvestment.setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, R.color.orange))
        }
    }

    override fun getItemCount(): Int {
        return listdata.size
    }
}
