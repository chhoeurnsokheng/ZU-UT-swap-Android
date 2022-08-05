package com.zillennium.utswap.module.project.projectInfoScreen.adapter

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
import com.zillennium.utswap.models.ProjectInfoInvestmentModel

class ProjectInfoInvestmentAdapter

    (arrayList: ArrayList<ProjectInfoInvestmentModel>) :
    RecyclerView.Adapter<ProjectInfoInvestmentAdapter.ViewHolder>() {
    private val listdata:  ArrayList<ProjectInfoInvestmentModel> = arrayList

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
        val projectInfoInvestmentModelList: ProjectInfoInvestmentModel = listdata[position]

        holder.txtPerUT.text = projectInfoInvestmentModelList.perUT
        holder.txtValueUT.text = projectInfoInvestmentModelList.valueUT
        holder.txtSqmUT.text = projectInfoInvestmentModelList.sqmUT

        if(position == 0)
        {
            holder.layoutInvestment.setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, R.color.gray_CCCCCC))
        }
        else if(position == 1){
            holder.layoutInvestment.setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, R.color.warning))
        }
    }

    override fun getItemCount(): Int {
        return listdata.size
    }
}
