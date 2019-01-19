package ru.avystavkin.githubgists.screen.gist_detail.holders

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import butterknife.BindView
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder

class GistSectionHolder(itemView: View) : BaseViewHolder(itemView) {

    @BindView(R.id.gist_section)
    protected lateinit var mName: TextView

    fun bind(name: String) {
        if (!TextUtils.isEmpty(name)) {
            mName.text = name
        }
    }
}
