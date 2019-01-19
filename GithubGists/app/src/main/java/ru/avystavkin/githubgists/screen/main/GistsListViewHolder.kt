package ru.avystavkin.githubgists.screen.main

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.empty_text_view.view.*
import kotlinx.android.synthetic.main.recylerview_vertical.view.*
import ru.avystavkin.githubgists.AppDelegate
import ru.avystavkin.githubgists.models.database.Gist
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder
import ru.avystavkin.githubgists.screen.interfaces.OnGistClickListner
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener
import ru.avystavkin.githubgists.screen.main.gist.GistsAdapter
import ru.avystavkin.githubgists.widget.DividerItemDecoration
import java.util.*

class GistsListViewHolder(private val mOnGistClickListener: OnGistClickListner?, itemView: View) : BaseViewHolder(itemView), OnItemClickListener<Gist> {

    private val mAdapter: GistsAdapter

    init {

        itemView.recyclerViewVertical.layoutManager = LinearLayoutManager(AppDelegate.context)
        itemView.recyclerViewVertical.addItemDecoration(DividerItemDecoration(AppDelegate.context))
        itemView.recyclerViewVertical.emptyView = itemView.emptyView

        mAdapter = GistsAdapter(ArrayList())
        mAdapter.attachToRecyclerView(itemView.recyclerViewVertical)
        mAdapter.onItemClickListener = this
    }

    fun bind(gists: List<Gist>) {
        mAdapter.changeDataSet(gists)
    }

    override fun onItemClick(gist: Gist) {
        mOnGistClickListener?.onGistClick(gist)
    }
}
