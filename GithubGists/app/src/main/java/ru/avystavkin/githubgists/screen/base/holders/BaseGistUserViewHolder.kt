package ru.avystavkin.githubgists.screen.base.holders

import android.view.View
import kotlinx.android.synthetic.main.item_gist.view.*
import ru.avystavkin.githubgists.models.database.Gist

abstract class BaseGistUserViewHolder(itemView: View) : BaseUserHolder(itemView) {

    fun bind(gist: Gist) {
        if (!gist.name.isNullOrEmpty())
            itemView.tvGistName.text = gist.name!!

        if (gist.user != null) {
            super.bind(gist.user!!)
        }
    }
}
