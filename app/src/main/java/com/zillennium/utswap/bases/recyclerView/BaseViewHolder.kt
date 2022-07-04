package com.zillennium.utswap.bases.recyclerView

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T : ViewDataBinding>(protected var binding: T) :
    RecyclerView.ViewHolder(binding.root) {

    protected val context: Context
        get() = binding.root.context

    protected fun getString(resId: Int): String {
        return context.resources.getString(resId)
    }

    protected fun getColor(resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    protected fun getDimensionPixelOffset(resId: Int): Int {
        return context.resources.getDimensionPixelOffset(resId)
    }

    protected fun getDimension(resId: Int): Float {
        return context.resources.getDimension(resId)
    }

    protected fun getDrawable(resId: Int?): Drawable? {
        if (resId == null) return null
        return ResourcesCompat.getDrawable(
            itemView.resources,
            resId,
            itemView.context.theme
        )
    }
}