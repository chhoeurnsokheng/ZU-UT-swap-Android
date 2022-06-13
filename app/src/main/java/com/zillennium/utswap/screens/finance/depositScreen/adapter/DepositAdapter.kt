package com.zillennium.utswap.screens.finance.depositScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.DepositModel
import com.zillennium.utswap.utils.dpToPx


class DepositAdapter(private val arrayList: ArrayList<DepositModel>, onclickDeposit: OnClickDeposit) :
    RecyclerView.Adapter<DepositAdapter.ViewHolder>(){

    private val listData: ArrayList<DepositModel> = arrayList
    private lateinit var onclickDeposit: OnClickDeposit


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var layDepositItem: LinearLayout = view.findViewById<LinearLayout>(R.id.layDepositItem)
        var card_image: ImageView = view.findViewById<View>(R.id.img_cardImage) as ImageView
        var card_name: TextView = view.findViewById<View>(R.id.tv_cardTitle) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepositAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_finance_deposit_payment, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val  depositCurrentItemList: DepositModel = listData[position]
        holder.card_image.setImageResource(depositCurrentItemList.cardImg)
        holder.card_name.text = depositCurrentItemList.cardTitle
        when(depositCurrentItemList.cardTitle)
        {
            "ABA Pay"->{
                holder.card_image.layoutParams.width = dpToPx(30)
                holder.card_image.layoutParams.height = dpToPx(18)
            }
            "Visa/ Master Card"->{
                holder.card_image.layoutParams.width = dpToPx(64)
                holder.card_image.layoutParams.height = dpToPx(19)
            }
            "Acleda Bank"->{
                holder.card_image.layoutParams.width = dpToPx(29)
                holder.card_image.layoutParams.height = dpToPx(29)
            }
            "Sathapana"->{
                holder.card_image.layoutParams.width = dpToPx(27)
                holder.card_image.layoutParams.height = dpToPx(32)
            }
        }

        holder.layDepositItem.setOnClickListener { onclickDeposit.ClickDepositCard(depositCurrentItemList.cardTitle, depositCurrentItemList.cardImg) }

    }
    override fun getItemCount(): Int {
        return listData.size
    }

    interface OnClickDeposit {
        fun ClickDepositCard(cardTitle: String,cardImg: Int)
    }
    init {
        this.onclickDeposit = onclickDeposit
    }

}
