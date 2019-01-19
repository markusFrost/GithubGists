package ru.avystavkin.githubgists.screen.base.holders

import android.view.View
import kotlinx.android.synthetic.main.item_gist.view.*
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.utils.Images

abstract class BaseUserHolder(itemView: View) : BaseViewHolder(itemView) {

    fun bind(user: User) {
        if (!user.name.isNullOrEmpty()) {
            itemView.tvGistUsername.text = user.name!!
        }

        if (!user.url.isNullOrEmpty()) {
            Images.loadImage(itemView.ivGistImg, user.url!!)
        }
    }
}
