package ru.avystavkin.githubgists.screen.base.adapters

import androidx.recyclerview.widget.RecyclerView
import ru.avystavkin.githubgists.widget.EmptyRecyclerView

abstract class BaseRecyclerViewAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private var mRecyclerView: EmptyRecyclerView? = null

    fun attachToRecyclerView(recyclerView: EmptyRecyclerView) {
        mRecyclerView = recyclerView
        mRecyclerView!!.adapter = this
        refreshRecycler()
    }

    protected fun refreshRecycler() {
        notifyDataSetChanged()
        mRecyclerView?.checkIfEmpty()
    }
}
