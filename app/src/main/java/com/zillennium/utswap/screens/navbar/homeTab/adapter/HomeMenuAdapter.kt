package com.zillennium.utswap.screens.navbar.homeTab.adapter
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.HomeMenuModel

class HomeMenuAdapter(
    private val arrayList: ArrayList<HomeMenuModel>,
    itemListHomeGrid: Int,
    onclickHome: OnclickHome
) :
    RecyclerView.Adapter<HomeMenuAdapter.ViewHolder>() {
    private var listdata: ArrayList<HomeMenuModel> = arrayList
    private var onclickHome: OnclickHome

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var txtTitle: TextView = view.findViewById<View>(R.id.title_home) as TextView
        var ivImage: ImageView = view.findViewById<View>(R.id.image_homeview) as ImageView
        var layItem: LinearLayout = view.findViewById<LinearLayout>(R.id.lay_item)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_home_grid, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeInfoDetailList: HomeMenuModel = listdata[position]
        holder.txtTitle.text = homeInfoDetailList.titleHome
        holder.ivImage.setImageResource(homeInfoDetailList.imageHome)
        holder.layItem.setOnClickListener { onclickHome.ClickDeposit(homeInfoDetailList.titleHome) }

        if(homeInfoDetailList.isEnabled){
            holder.txtTitle.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.primary)))
            holder.ivImage.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
            holder.layItem.isEnabled = true

        }else{
            holder.txtTitle.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.gray_999999)))
            holder.ivImage.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.gray_999999))
            holder.layItem.isEnabled = false
        }
    }


    override fun getItemCount(): Int {
        return listdata.size
    }

    interface OnclickHome {
        fun ClickDeposit(title: String)
    }

    init {
        this.onclickHome = onclickHome
    }
}


