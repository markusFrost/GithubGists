package ru.avystavkin.githubgists.screen.gist_detail.holders

import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.item_gist_content.view.*
import ru.avystavkin.githubgists.models.database.Gist
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder

class GistDetailContentViewHolder(itemView: View) : BaseViewHolder(itemView) {

    fun bind(gist: Gist, position: Int) {
        if (position >= gist.listFiles!!.size)
            return

        val file = gist.listFiles!![position]
        if (!TextUtils.isEmpty(file.fileName))
            itemView.tvGistFilename.text = file.fileName

        if (!TextUtils.isEmpty(file.content))
            itemView.tvGistCodeContent.text = file.content
    }
}
