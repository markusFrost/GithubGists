package ru.avystavkin.githubgists.screen.main

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import ru.avystavkin.githubgists.AppDelegate
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener
import ru.avystavkin.githubgists.screen.interfaces.OnUserClickListener
import ru.avystavkin.githubgists.screen.main.users.UserPopularAdapter
import ru.avystavkin.githubgists.widget.DividerItemDecoration
import ru.avystavkin.githubgists.widget.EmptyRecyclerView
import java.util.*

class UsersListViewHolder(private val mOnUserClickListener: OnUserClickListener?, itemView: View) : BaseViewHolder(itemView), OnItemClickListener<User> {

    @BindView(R.id.recyclerViewHorizontal)
    internal lateinit var mRecyclerView: EmptyRecyclerView

    @BindView(R.id.empty)
    internal lateinit var mEmptyView: View

    private val mAdapter: UserPopularAdapter

    init {

        val horizontalLayoutManager = LinearLayoutManager(AppDelegate.context, LinearLayoutManager.HORIZONTAL, false)

        mRecyclerView.layoutManager = horizontalLayoutManager
        mRecyclerView.addItemDecoration(DividerItemDecoration(AppDelegate.context))
        mRecyclerView.emptyView = mEmptyView

        mAdapter = UserPopularAdapter(ArrayList())
        mAdapter.attachToRecyclerView(mRecyclerView)
        mAdapter.onItemClickListener = this
    }

    fun bind(users: List<User>) {
        mAdapter.changeDataSet(users)
    }

    override fun onItemClick(user: User) {
        mOnUserClickListener?.onUserClick(user)
    }
}
