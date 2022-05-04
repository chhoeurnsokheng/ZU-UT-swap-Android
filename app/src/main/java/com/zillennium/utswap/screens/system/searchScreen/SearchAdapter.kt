package com.zillennium.utswap.screens.system.searchScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R

class SearchAdapter(
    private val listdata: ArrayList<Search>,
    private val onclickItemSearch: OnclickItemSearch
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_system_search, viewGroup, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val search = listdata[i]
        viewHolder.txtHistory.text = search.title
        viewHolder.txtHistory.setOnClickListener { onclickItemSearch.onClickMe(search.title) }
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtHistory: TextView = view.findViewById<View>(R.id.txt_history) as TextView
    }

    interface OnclickItemSearch {
        fun onClickMe(textSearch: String?)
    }
}
