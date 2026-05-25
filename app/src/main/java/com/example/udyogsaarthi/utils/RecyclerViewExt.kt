package com.example.udyogsaarthi.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.udyogsaarthi.R

/**
 * Draws a thin horizontal divider between RecyclerView items.
 * Respects the item's horizontal margins so the line doesn't span edge-to-edge.
 *
 * Usage:
 *   recyclerView.addItemDecoration(MarginDividerDecoration(requireContext()))
 */
class MarginDividerDecoration(
    context: Context,
    private val marginStartDp: Int = 16,
    private val marginEndDp: Int = 16
) : RecyclerView.ItemDecoration() {

    private val divider: Drawable? =
        ContextCompat.getDrawable(context, R.drawable.divider_item)

    private val density = context.resources.displayMetrics.density

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        divider ?: return
        val left  = parent.paddingLeft  + (marginStartDp * density).toInt()
        val right = parent.width - parent.paddingRight - (marginEndDp * density).toInt()

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top    = child.bottom + params.bottomMargin
            val bottom = top + (divider.intrinsicHeight.coerceAtLeast(1))
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}
