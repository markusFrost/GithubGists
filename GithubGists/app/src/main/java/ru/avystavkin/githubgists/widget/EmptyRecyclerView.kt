package ru.avystavkin.githubgists.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView

public class EmptyRecyclerView : RecyclerView {

    lateinit var emptyView: View

    //region constructors
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}
    //endregion

    fun checkIfEmpty() {
        if (adapter!!.itemCount > 0) {
            showRecycler()
        } else {
            showEmptyView()
        }
    }

    @VisibleForTesting
    internal fun showRecycler() {
        emptyView.visibility = View.GONE
        visibility = View.VISIBLE
    }

    @VisibleForTesting
    internal fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
        visibility = View.GONE
    }
}


