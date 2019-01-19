package ru.avystavkin.githubgists.screen.gist_detail.holders

import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.item_gist_commits.view.*
import ru.avystavkin.githubgists.AppDelegate
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.database.GistCommit
import ru.avystavkin.githubgists.screen.base.holders.BaseGistUserViewHolder

class GistDetailCommitViewHolder(itemView: View) : BaseGistUserViewHolder(itemView) {

    fun bind(list: List<GistCommit>, position: Int) {
        if (position >= list.size)
            return
        val history = list[position]
        super.bind(history.user!!)
        val status = history.commitsHistory
        if (status != null) {
            itemView.tvGistCommitTotal.text = "${AppDelegate.context.resources.getString(R.string.commits_total)}: ${status.total}"
            itemView.tvGistCommitAdditions.text = "${AppDelegate.context.resources.getString(R.string.commits_additions)}: ${status.additions}"
            itemView.tvGistCommitDeletions.text = "${AppDelegate.context.resources.getString(R.string.commits_deletions)}: ${status.deletions}"
        }

        val dateStr = history.commitDateTime
        if (!TextUtils.isEmpty(dateStr)) {
            itemView.tvGistName.text = "${ AppDelegate.context.resources.getString(R.string.commits_date) }" +
                    " ${  dateStr!!.replace("T", " ").replace("Z", "") }"
        }
    }
}
