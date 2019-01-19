package ru.avystavkin.githubgists.screen.main

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.empty_text_view.view.*
import kotlinx.android.synthetic.main.recylerview_horizontal.view.*
import ru.avystavkin.githubgists.AppDelegate
import ru.avystavkin.githubgists.models.database.User
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener
import ru.avystavkin.githubgists.screen.interfaces.OnUserClickListener
import ru.avystavkin.githubgists.screen.main.users.UserPopularAdapter
import ru.avystavkin.githubgists.widget.DividerItemDecoration
import java.util.*

class UsersListViewHolder(private val mOnUserClickListener: OnUserClickListener?, itemView: View) : BaseViewHolder(itemView), OnItemClickListener<User> {

    private val mAdapter: UserPopularAdapter

    init {

        val horizontalLayoutManager = LinearLayoutManager(AppDelegate.context, LinearLayoutManager.HORIZONTAL, false)

        itemView.recyclerViewHorizontal.layoutManager = horizontalLayoutManager
        itemView.recyclerViewHorizontal.addItemDecoration(DividerItemDecoration(AppDelegate.context))
        itemView.recyclerViewHorizontal.emptyView = itemView.emptyView

        mAdapter = UserPopularAdapter(ArrayList())
        mAdapter.attachToRecyclerView(itemView.recyclerViewHorizontal)
        mAdapter.onItemClickListener = this
    }

    fun bind(users: List<User>) {
        mAdapter.changeDataSet(users)
    }

    override fun onItemClick(user: User) {
        mOnUserClickListener?.onUserClick(user)
    }
}
