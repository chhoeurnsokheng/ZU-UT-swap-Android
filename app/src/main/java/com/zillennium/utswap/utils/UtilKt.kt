package com.zillennium.utswap.utils

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Sokheng Chhoeurn on 27/7/22.
 * Build in Mac
 */

class UtilKt {
    fun dpToPx(context: Context, dp: Int): Int {
        val r = context.resources
        val px =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics)
        return px.toInt()
    }

}
class SpaceDecoration(
    private val leftSpace: Int = 0,
    private val topSpace: Int = 0,
    private val rightSpace: Int = 0,
    private val bottomSpace: Int = 0,
    private val skipFirst: Boolean = true
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if ((!skipFirst && position == 0) || position > 0) {
            outRect.set(leftSpace, topSpace, rightSpace, bottomSpace)
        }
    }
}