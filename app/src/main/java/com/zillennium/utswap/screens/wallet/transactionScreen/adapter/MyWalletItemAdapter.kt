package com.zillennium.utswap.screens.wallet.transactionScreen.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.screens.wallet.transactionScreen.model.MyWalletItem

class MyWalletItemAdapter(arrayList: ArrayList<MyWalletItem?>) :
    RecyclerView.Adapter<MyWalletItemAdapter.ViewHolder>() {
    private var arrayList: ArrayList<MyWalletItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_wallet_balance_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myWalletItem: MyWalletItem = arrayList[position]
        holder.img.setImageResource(myWalletItem.imagePath)
        holder.txtDate.text = myWalletItem.date
        holder.txtName.text = myWalletItem.transactionDetail
        //
        if (myWalletItem.moneyIn == " ") {
            holder.txtMoney.text = myWalletItem.moneyOut
        } else if (myWalletItem.moneyIn != " ") {
            holder.txtMoney.setTextColor(
                ContextCompat.getColor(
                    holder.container.context,
                    R.color.success
                )
            )
            holder.img.imageTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.container.context,
                        R.color.success
                    )
                )
            holder.img.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.container.context,
                        R.color.success
                    )
                )
            holder.txtMoney.text = myWalletItem.moneyIn
        }
        if (arrayList.size == 1) {
            holder.line.visibility = View.GONE
        } else {
            if (arrayList.size - 1 == position) {
                holder.line.visibility = View.GONE
            } else if (0 == position) {
                holder.itemView.setBackgroundResource(R.drawable.card_view_whtie_border_top)
                holder.line.visibility = View.VISIBLE
            } else {
                holder.itemView.setBackgroundResource(R.color.white)
                holder.line.visibility = View.VISIBLE
            }
        }
        holder.itemView.setOnClickListener { view ->
            val fragmentManager = (view.context as FragmentActivity).supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            //                BalanceHistoryDetailDialog balanceHistoryDetailDialog = BalanceHistoryDetailDialog.newInstance(myWalletItem.getTransaction(),myWalletItem.getTransactionDetail(), myWalletItem.getDate(), myWalletItem.getMoneyOut(), myWalletItem.getBalance(),myWalletItem.getMoneyIn(),myWalletItem.getImagePath());
            //                balanceHistoryDetailDialog.show(fragmentTransaction, "My Wallet Item");
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        val img: ImageView = itemView.findViewById(R.id.img_cash)
        val line: View = itemView.findViewById(R.id.line)
        val txtMoney: TextView = itemView.findViewById(R.id.txt_money)
        val txtName: TextView = itemView.findViewById(R.id.txt_name)
        val container: LinearLayout = itemView.findViewById(R.id.container_balance_history)

    }
}
