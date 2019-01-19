package ru.avystavkin.githubgists.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val mDivider: Drawable?

    @RecyclerView.Orientation
    private var mOrientation = RecyclerView.HORIZONTAL

    init {
        val typedArray = context
                .obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
        mDivider = typedArray.getDrawable(0)
        typedArray.recycle()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (mDivider == null) {
            return
        }

        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION || position == 0) {
            return
        }

        if (mOrientation == RecyclerView.HORIZONTAL) {
            getOrientation(parent)
        }

        if (mOrientation == RecyclerView.VERTICAL) {
            outRect.top = mDivider.intrinsicHeight
        } else {
            outRect.left = mDivider.intrinsicWidth
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mDivider == null) {
            super.onDrawOver(c, parent, state)
            return
        }

        // Initialization needed to avoid compiler warning
        var left = 0
        var right = 0
        var top = 0
        var bottom = 0
        val size: Int
        val orientation = if (mOrientation != RecyclerView.HORIZONTAL) mOrientation else getOrientation(parent)
        val childCount = parent.childCount

        if (orientation == LinearLayoutManager.VERTICAL) {
            size = mDivider.intrinsicHeight
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
        } else { //horizontal
            size = mDivider.intrinsicWidth
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
        }

        for (index in 1 until childCount) {
            val child = parent.getChildAt(index)
            val params = child.layoutParams as RecyclerView.LayoutParams

            if (orientation == LinearLayoutManager.VERTICAL) {
                top = child.top - params.topMargin - size
                bottom = top + size
            } else { //horizontal
                left = child.left - params.leftMargin
                right = left + size
            }
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }

        // show last divider
    }

    private fun getOrientation(parent: RecyclerView): Int {
        if (mOrientation == RecyclerView.HORIZONTAL) {
            if (parent.layoutManager is LinearLayoutManager) {
                val layoutManager = parent.layoutManager as LinearLayoutManager?
                mOrientation = layoutManager!!.orientation
            }
        }
        return mOrientation
    }
}
