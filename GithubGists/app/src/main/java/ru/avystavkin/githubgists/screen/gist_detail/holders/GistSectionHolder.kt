package ru.avystavkin.githubgists.screen.gist_detail.holders

import android.view.View
import kotlinx.android.synthetic.main.item_gist_section.view.*
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder

class GistSectionHolder(itemView: View) : BaseViewHolder(itemView) {

    fun bind(name: String) {
        itemView.tvGistSection.text = name
    }
}
