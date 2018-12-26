package ru.avystavkin.githubgists.screen.gists.detail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistFile;
import ru.avystavkin.githubgists.content.GistHistory;
import ru.avystavkin.githubgists.screen.gists.holders.GistDetailCommitsHolder;
import ru.avystavkin.githubgists.screen.gists.holders.GistDetailContentHolder;
import ru.avystavkin.githubgists.screen.gists.holders.GistDetailHeaderHolder;
import ru.avystavkin.githubgists.screen.gists.holders.GistSectionHolder;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;

public class GistDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
    private List<GistHistory> mListCommits;

    @Nullable
    private EmptyRecyclerView mRecyclerView;

    public GistDetailAdapter() {
       mListCommits = new ArrayList<>();
    }

    public void setCommits(@NonNull List<GistHistory> commits) {
        if (commits == null)
            return;
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
        if (mListCommits == null)
            return 0;
        return mListCommits.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == HEADER_TYPE) {
            return new GistDetailHeaderHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gist, parent, false));
        }
        else if (viewType == COMMITS_SECTION_TYPE || viewType == CONTENT_SECTION_TYPE ) {
            return new GistSectionHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gist_section, parent, false));
        }
        else if (viewType == CONTENT_TYPE ) {
            return new GistDetailContentHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gist_content, parent, false));
        }
        else if (viewType == COMMIT_TYPE) {
            return new GistDetailCommitsHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_gist_commits, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        switch (getItemViewType(position)) {
            case HEADER_TYPE: {
                ((GistDetailHeaderHolder)holder).bind(mGist);
            }break;
            case CONTENT_SECTION_TYPE: {
                ((GistSectionHolder)holder).bind("Content");
            }break;
            case CONTENT_TYPE: {
                ((GistDetailContentHolder)holder).bind(mGist, position - mContentSectionIndex - 1);
            }break;
            case COMMITS_SECTION_TYPE: {
                ((GistSectionHolder)holder).bind("Commits");
            }break;
            case COMMIT_TYPE: {
                ((GistDetailCommitsHolder)holder).bind(mListCommits, position - mCommitSectionIndex - 1);
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

    public void attachToRecyclerView(@NonNull EmptyRecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setAdapter(this);
        refreshRecycler();
    }

    public void refreshRecycler() {
        notifyDataSetChanged();
        if (mRecyclerView != null) {
            mRecyclerView.checkIfEmpty();
        }
    }
}
