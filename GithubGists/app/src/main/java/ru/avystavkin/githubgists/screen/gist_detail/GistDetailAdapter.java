package ru.avystavkin.githubgists.screen.gist_detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.server.Gist;
import ru.avystavkin.githubgists.content.server.GistFile;
import ru.avystavkin.githubgists.content.server.GistHistory;
import ru.avystavkin.githubgists.screen.base.adapters.BaseRecyclerViewAdapter;
import ru.avystavkin.githubgists.screen.gist_detail.holders.GistDetailCommitViewHolder;
import ru.avystavkin.githubgists.screen.gist_detail.holders.GistDetailContentViewHolder;
import ru.avystavkin.githubgists.screen.gist_detail.holders.GistSectionHolder;
import ru.avystavkin.githubgists.screen.gist_detail.holders.UserHeaderViewHolder;

public class GistDetailAdapter extends BaseRecyclerViewAdapter {

    private static final int HEADER_INDEX = 0;
    private static final int HEADER_TYPE = 0;
    private static final int CONTENT_TYPE = 1;
    private static final int CONTENT_SECTION_TYPE = 2;
    private static final int COMMIT_TYPE = 3;
    private static final int COMMITS_SECTION_TYPE = 4;

    private int mContentSectionIndex;
    private int mCommitSectionIndex;

    private int mContentBodyLastIndex;
    private int mCommitBodyFirstIndex;

    private Gist mGist;
    private List<GistHistory> mListCommits = new ArrayList<>();

    public void setCommits(@NonNull List<GistHistory> commits) {
        mListCommits.clear();
        mListCommits.addAll(commits);
        notifyDataSetChanged();
    }

    public void setGist(@NonNull Gist gist) {
        mGist = gist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        final int headersCount = 1;
        final int contentsCount =  getContentCount();
        final int commitsCount = getCommitsCount();
        int sectionsCount = 0;

        if (contentsCount > 0) {
            mContentSectionIndex = headersCount;
            mContentBodyLastIndex = mContentSectionIndex + contentsCount;
            sectionsCount++;
        }

        if (commitsCount > 0) {
            mCommitSectionIndex = mContentBodyLastIndex + 1;
            mCommitBodyFirstIndex = mCommitSectionIndex + 1;
            sectionsCount++;
        }

        return headersCount + contentsCount + commitsCount + sectionsCount;
    }

    private int getContentCount() {
        if (mGist == null || mGist.getRawFiles() == null)
            return 0;
        List<GistFile> list = mGist.getGistFiles();
        return list.size();
    }

    private int getCommitsCount() {
        return mListCommits.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == HEADER_TYPE) {
            return new UserHeaderViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gist, parent, false));
        }
        else if (viewType == COMMITS_SECTION_TYPE || viewType == CONTENT_SECTION_TYPE ) {
            return new GistSectionHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gist_section, parent, false));
        }
        else if (viewType == CONTENT_TYPE ) {
            return new GistDetailContentViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gist_content, parent, false));
        }
        else if (viewType == COMMIT_TYPE) {
            return new GistDetailCommitViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gist_commits, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        switch (getItemViewType(position)) {
            case HEADER_TYPE: {
                ((UserHeaderViewHolder)holder).bind(mGist);
            }break;
            case CONTENT_SECTION_TYPE: {
                ((GistSectionHolder)holder).bind(AppDelegate.getContext().getResources().getString(R.string.content_section));
            }break;
            case CONTENT_TYPE: {
                ((GistDetailContentViewHolder)holder).bind(mGist, position - mContentSectionIndex - 1);
            }break;
            case COMMITS_SECTION_TYPE: {
                ((GistSectionHolder)holder).bind(AppDelegate.getContext().getResources().getString(R.string.commits_section));
            }break;
            case COMMIT_TYPE: {
                ((GistDetailCommitViewHolder)holder).bind(mListCommits, position - mCommitSectionIndex - 1);
            }break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HEADER_INDEX) {
            return HEADER_TYPE;
        }
        else if (position == mContentSectionIndex) {
            return CONTENT_SECTION_TYPE;
        }
        else if (position > HEADER_INDEX && position <= mContentBodyLastIndex) {
            return CONTENT_TYPE;
        }
        else if (position == mCommitSectionIndex) {
            return COMMITS_SECTION_TYPE;
        }
        else if (position >= mCommitBodyFirstIndex) {
            return COMMIT_TYPE;
        }
        return position;
    }
}
