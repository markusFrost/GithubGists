package ru.avystavkin.githubgists.screen.main.users


import android.view.LayoutInflater
import android.view.ViewGroup
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.database.User
import ru.avystavkin.githubgists.screen.base.adapters.BaseAdapter


class UserPopularAdapter(items: List<User>) : BaseAdapter<UserViewHolder, User>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }
}

