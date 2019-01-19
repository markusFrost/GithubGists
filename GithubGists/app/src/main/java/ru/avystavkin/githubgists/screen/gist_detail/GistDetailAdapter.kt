package ru.avystavkin.githubgists.screen.gist_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.avystavkin.githubgists.AppDelegate
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.models.local.GistCommit
import ru.avystavkin.githubgists.screen.base.adapters.BaseRecyclerViewAdapter
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder
import ru.avystavkin.githubgists.screen.gist_detail.holders.GistDetailCommitViewHolder
import ru.avystavkin.githubgists.screen.gist_detail.holders.GistDetailContentViewHolder
import ru.avystavkin.githubgists.screen.gist_detail.holders.GistSectionHolder
import ru.avystavkin.githubgists.screen.gist_detail.holders.UserHeaderViewHolder
import java.util.*

class GistDetailAdapter : BaseRecyclerViewAdapter<BaseViewHolder>() {

    private var mContentSectionIndex: Int = 0
    private var mCommitSectionIndex: Int = 0

    private var mContentBodyLastIndex: Int = 0
    private var mCommitBodyFirstIndex: Int = 0

    private var mGist: Gist? = null
    private val mListCommits = ArrayList<GistCommit>()

    private val contentCount: Int
        get() = if (mGist == null || mGist!!.listFiles == null) 0 else mGist!!.listFiles!!.size

    private val commitsCount: Int
        get() = mListCommits.size

    fun setCommits(commits: List<GistCommit>) {
        mListCommits.clear()
        mListCommits.addAll(commits)
        notifyDataSetChanged()
    }

    fun setGist(gist: Gist) {
        mGist = gist
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        val headersCount = 1
        val contentsCount = contentCount
        val commitsCount = commitsCount
        var sectionsCount = 0

        if (contentsCount > 0) {
            mContentSectionIndex = headersCount
            mContentBodyLastIndex = mContentSectionIndex + contentsCount
            sectionsCount++
        }

        if (commitsCount > 0) {
            mCommitSectionIndex = mContentBodyLastIndex + 1
            mCommitBodyFirstIndex = mCommitSectionIndex + 1
            sectionsCount++
        }

        return headersCount + contentsCount + commitsCount + sectionsCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return if (viewType == HEADER_TYPE) {
            UserHeaderViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_gist, parent, false))
        } else if (viewType == COMMITS_SECTION_TYPE || viewType == CONTENT_SECTION_TYPE) {
            GistSectionHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_gist_section, parent, false))
        } else if (viewType == CONTENT_TYPE) {
            GistDetailContentViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_gist_content, parent, false))
        } else {
            GistDetailCommitViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_gist_commits, parent, false))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.tag = position
        when (getItemViewType(position)) {
            HEADER_TYPE -> {
                (holder as UserHeaderViewHolder).bind(mGist!!)
            }
            CONTENT_SECTION_TYPE -> {
                (holder as GistSectionHolder).bind(AppDelegate.context.resources.getString(R.string.content_section))
            }
            CONTENT_TYPE -> {
                (holder as GistDetailContentViewHolder).bind(mGist!!, position - mContentSectionIndex - 1)
            }
            COMMITS_SECTION_TYPE -> {
                (holder as GistSectionHolder).bind(AppDelegate.context.resources.getString(R.string.commits_section))
            }
            COMMIT_TYPE -> {
                (holder as GistDetailCommitViewHolder).bind(mListCommits, position - mCommitSectionIndex - 1)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == HEADER_INDEX) {
            return HEADER_TYPE
        } else if (position == mContentSectionIndex) {
            return CONTENT_SECTION_TYPE
        } else if (position > HEADER_INDEX && position <= mContentBodyLastIndex) {
            return CONTENT_TYPE
        } else if (position == mCommitSectionIndex) {
            return COMMITS_SECTION_TYPE
        } else if (position >= mCommitBodyFirstIndex) {
            return COMMIT_TYPE
        }
        return position
    }

    companion object {

        private val HEADER_INDEX = 0
        private val HEADER_TYPE = 0
        private val CONTENT_TYPE = 1
        private val CONTENT_SECTION_TYPE = 2
        private val COMMIT_TYPE = 3
        private val COMMITS_SECTION_TYPE = 4
    }
}
