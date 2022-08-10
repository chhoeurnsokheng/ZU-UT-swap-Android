package com.zillennium.utswap.module.kyc.kycFragment.dropDownAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.province.PProvinceObj

class DropDownAdapter(
    var itemList: ArrayList<Any>,
    var context: Context,
    var type: String,
    var onclickListener: OnclickListener,
) : RecyclerView.Adapter<DropDownAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DropDownAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_drop_down, parent, false))
    }

    override fun onBindViewHolder(holder: DropDownAdapter.ViewHolder, position: Int) {
        if (type.isNotEmpty()) {
            val item = itemList[position] as PProvinceObj.Items
            holder.tvValue.text = item.english
            holder.itemView.setOnClickListener {
                when (type) {
                    "province" -> {
                        onclickListener.onItemClick(
                            item.english.toString(),
                            "",
                            "",
                            item.code.toString(),
                            "",
                            ""
                        )

                    }
                    "district" -> {
                        onclickListener.onItemClick(
                            "",
                            item.english.toString(),
                            "",
                            "",
                            item.code.toString(),""
                        )

                    }
                    "commune" -> {
                        onclickListener.onItemClick("", "", item.english.toString(), "", "","")

                    }
                }

            }
        } else {
            holder.tvValue.text = itemList[position].toString()
            holder.itemView.setOnClickListener {
                onclickListener.onItemClick(
                    "",
                    "",
                    "",
                    "",
                    "",
                    itemList[position].toString()
                )
            }

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvValue: TextView = view.findViewById(R.id.tv_value)
        var llValue: LinearLayout = view.findViewById(R.id.ll_value)

    }

    interface OnclickListener {
        fun onItemClick(textProvince: String, textDistrict: String, textCommune: String, provinceCode: String, districtCode: String, textGender:String)
    }


}