package ru.avystavkin.githubgists.screen.main

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import ru.avystavkin.githubgists.AppDelegate
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder
import ru.avystavkin.githubgists.screen.interfaces.OnGistClickListner
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener
import ru.avystavkin.githubgists.screen.main.gist.GistsAdapter
import ru.avystavkin.githubgists.widget.DividerItemDecoration
import ru.avystavkin.githubgists.widget.EmptyRecyclerView
import java.util.*

class GistsListViewHolder(private val mOnGistClickListener: OnGistClickListner?, itemView: View) : BaseViewHolder(itemView), OnItemClickListener<Gist> {

    @BindView(R.id.recyclerViewVertical)
    internal lateinit var mRecyclerView: EmptyRecyclerView

    @BindView(R.id.empty)
    internal lateinit var mEmptyView: View

    private val mAdapter: GistsAdapter

    init {

        mRecyclerView.layoutManager = LinearLayoutManager(AppDelegate.context)
        mRecyclerView.addItemDecoration(DividerItemDecoration(AppDelegate.context))
        mRecyclerView.emptyView = mEmptyView

        mAdapter = GistsAdapter(ArrayList())
        mAdapter.attachToRecyclerView(mRecyclerView)
        mAdapter.onItemClickListener = this
    }

    fun bind(gists: List<Gist>) {
        mAdapter.changeDataSet(gists)
    }

    override fun onItemClick(gist: Gist) {
        mOnGistClickListener?.onGistClick(gist)
    }
}
