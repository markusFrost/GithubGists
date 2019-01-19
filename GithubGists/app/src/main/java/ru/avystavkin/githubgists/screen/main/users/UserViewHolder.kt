package ru.avystavkin.githubgists.screen.main.users

import android.view.View
import kotlinx.android.synthetic.main.item_user.view.*
import ru.avystavkin.githubgists.models.database.User
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder
import ru.avystavkin.githubgists.utils.Images

class UserViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun bind(user: User) {

        if (!user.name.isNullOrEmpty()) {
            itemView.user_name.text = "${user.name!!} ( ${user.gistsCount} )"
        }

        if (!user.url.isNullOrEmpty()) {
            Images.loadImage(itemView.user_img, user.url!!)
        }
    }
}

