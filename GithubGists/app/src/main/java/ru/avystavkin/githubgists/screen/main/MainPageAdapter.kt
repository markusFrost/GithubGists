package ru.avystavkin.githubgists.screen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.screen.base.adapters.BaseRecyclerViewAdapter
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder
import ru.avystavkin.githubgists.screen.interfaces.OnGistClickListner
import ru.avystavkin.githubgists.screen.interfaces.OnMainPageClickListner
import ru.avystavkin.githubgists.screen.interfaces.OnUserClickListener
import java.util.*

class MainPageAdapter(private val mOnMainPageClickListner: OnMainPageClickListner?) : BaseRecyclerViewAdapter<BaseViewHolder>(), OnGistClickListner, OnUserClickListener {

    private val mListUsers = ArrayList<User>()
    private val mLisGists = ArrayList<Gist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if (viewType == USERS_LIST_VIEW_TYPE) {
            UsersListViewHolder(this, LayoutInflater.from(parent.context)
                    .inflate(R.layout.recylerview_horizontal, parent, false))
        } else {
            GistsListViewHolder(this, LayoutInflater.from(parent.context)
                    .inflate(R.layout.recylerview_vertical, parent, false))
        }
    }

    override fun onBindViewHolder(holder:BaseViewHolder, position: Int) {
        if (getItemViewType(position) == USERS_LIST_VIEW_TYPE)
            (holder as UsersListViewHolder).bind(mListUsers)
        else
            (holder as GistsListViewHolder).bind(mLisGists)
    }

    override fun getItemCount(): Int {
        var count = 0
        if (mLisGists.size > 0)
            count++

        if (mListUsers.size > 0)
            count++

        return count
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return if (mListUsers.size > 0)
                USERS_LIST_VIEW_TYPE
            else
                GISTS_LIST_VIEW_TYPE
        } else if (position == 1)
            return GISTS_LIST_VIEW_TYPE
        return position
    }

    fun clear() {
        mListUsers.clear()
        mLisGists.clear()

        refreshRecycler()
    }

    fun setListUsers(listUsers: List<User>) {
        mListUsers.clear()
        mListUsers.addAll(listUsers)
        refreshRecycler()
    }

    fun setListGists(listGists: List<Gist>) {
        mLisGists.clear()
        mLisGists.addAll(listGists)
        refreshRecycler()
    }

    override fun onGistClick(gist: Gist) {
        mOnMainPageClickListner?.onGistClick(gist)
    }

    override fun onUserClick(user: User) {
        mOnMainPageClickListner?.onUserClick(user)
    }

    companion object {

        private val USERS_LIST_VIEW_TYPE = 0
        private val GISTS_LIST_VIEW_TYPE = 1
    }
}
