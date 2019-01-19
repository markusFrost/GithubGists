package ru.avystavkin.githubgists.screen.gist_detail.holders

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import butterknife.BindView
import ru.avystavkin.githubgists.AppDelegate
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.GistCommit
import ru.avystavkin.githubgists.screen.base.holders.BaseGistUserViewHolder

class GistDetailCommitViewHolder(itemView: View) : BaseGistUserViewHolder(itemView) {

    @BindView(R.id.gist_commit_total)
    internal lateinit var mTotal: TextView

    @BindView(R.id.gist_commit_additions)
    internal lateinit var mAdditions: TextView

    @BindView(R.id.gist_commit_deletions)
    internal lateinit var mDeletions: TextView

    fun bind(list: List<GistCommit>, position: Int) {
        if (position >= list.size)
            return
        val history = list[position]
        super.bind(history.user!!)
        val status = history.commitsHistory
        if (status != null) {
            mTotal.text = String.format("%s: %d", AppDelegate.context.resources.getString(R.string.commits_total), status.total)
            mAdditions.text = String.format("%s: %d", AppDelegate.context.resources.getString(R.string.commits_additions), status.additions)
            mDeletions.text = String.format("%s: %d", AppDelegate.context.resources.getString(R.string.commits_deletions), status.deletions)
        }

        val dateStr = history.commitDateTime
        if (!TextUtils.isEmpty(dateStr)) {
            name.text = String.format("%s %s",
                    AppDelegate.context.resources.getString(R.string.commits_date),
                    dateStr!!.replace("T", " ").replace("Z", ""))
        }
    }
}
