package ru.avystavkin.githubgists.screen.base.holders

import android.view.View
import android.widget.TextView
import butterknife.BindView
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.Gist

abstract class BaseGistUserViewHolder(itemView: View) : BaseUserHolder(itemView) {

    @BindView(R.id.gist_name)
    protected lateinit var name: TextView

    fun bind(gist: Gist) {
        if (gist.name!!.isNotEmpty())
            name.text = gist.name!!

        if (gist.user != null) {
            super.bind(gist.user!!)
        }
    }
}
