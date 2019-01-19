package ru.avystavkin.githubgists.screen.gist_detail.holders

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import butterknife.BindView
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder

class GistDetailContentViewHolder(itemView: View) : BaseViewHolder(itemView) {
    @BindView(R.id.gist_file_name)
    internal lateinit var mFileName: TextView

    @BindView(R.id.gist_code_content)
    internal lateinit var mCodeContent: TextView

    fun bind(gist: Gist, position: Int) {
        if (position >= gist.listFiles!!.size)
            return

        val file = gist.listFiles!![position]
        if (!TextUtils.isEmpty(file.fileName))
            mFileName.text = file.fileName

        if (!TextUtils.isEmpty(file.content))
            mCodeContent.text = file.content
    }
}
