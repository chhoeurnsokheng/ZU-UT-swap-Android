package com.zillennium.utswap.bases.recyclerView

import android.text.InputFilter
import android.view.View
import android.widget.EditText
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected val VIEW_TYPE_LINE = -100
    protected val VIEW_TYPE_DEFAULT = 0
    protected val VIEW_TYPE_EMPTY = 1
    protected val VIEW_TYPE_LOADING = 2
    protected val VIEW_TYPE_NORMAL = 3
    protected val VIEW_TYPE_HEADER = 4
    protected val VIEW_TYPE_FOOTER = 5
    protected val VIEW_TYPE_SECTION = 6
    protected val VIEW_OTHER = 7
    protected val VIEW_NO_INTERNET = 8


    protected lateinit var viewDataBinding: ViewDataBinding

    var itemLists: MutableList<Any> = mutableListOf()

    abstract fun onItemSelect(item: Any)
    abstract fun onItemSelect(item: Any, position: Int)
    abstract fun onItemSelect(item: Any, position: Int, isCheck: Boolean)
    abstract fun onItemSelect(item: Any, position: Int, list: MutableList<Any>)
    abstract fun onRemoveItem(position: Int, size: Int)
    abstract fun onChildSelect(item: Any, position: Int)

    /**
     * setItems
     *
     * @param items type MutableList
     *
     * @return
     *
     */
    fun setItems(items: MutableList<Any>) {
        itemLists = items
        notifyDataSetChanged()
    }

    /**
     * notifyByIndex
     *
     * @param index type Int
     * @param data type Any
     *
     * @return
     *
     */
    fun notifyByIndex(index: Int, data: Any) {
        notifyItemChanged(index, data)
    }

    /**
     * appendItem
     *
     * @param data type Any
     *
     * @return
     *
     */
    fun appendItem(item: Any) {
        itemLists.add(item)
        notifyDataSetChanged()
    }

    /**
     * loading
     *
     * @return
     *
     */
    fun loading() {
        itemLists.clear()
//        itemLists.add(UtilsObject.Empty(title = "No Data", viewType = R.mipmap.ic_launcher))
    }

    /**
     * getListItems
     *
     * @return  itemLists
     *
     */
    fun getListItems(): MutableList<Any> {
        return itemLists
    }

    /**
     * clearAll
     *
     * @return
     *
     */
    fun clearAll() {
        itemLists.clear()
        notifyDataSetChanged()
    }

    /**
     * getItemSize
     *
     * @return  itemLists.size
     *
     */
    fun getItemSize(): Int {
        return itemLists.size
    }

    internal inner class ViewHolderLoading(itemView: View) : RecyclerView.ViewHolder(itemView)


    open class ViewHolderLine(itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {
        var viewDataBinding = itemView
    }

    class ViewHolderEmpty(
        itemView: View,
        private val mViewDataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Any) {
//            mViewDataBinding.setVariable(BR.Empty, data)
        }
    }

    open class ViewHolderDefault(itemView: ViewDataBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var viewDataBinding = itemView
    }

    fun EditText.setMaxLength(maxLength: Int) {
        filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
    }

}
