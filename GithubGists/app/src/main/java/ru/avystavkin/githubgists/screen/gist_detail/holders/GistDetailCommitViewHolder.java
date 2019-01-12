package ru.avystavkin.githubgists.screen.gist_detail.holders;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.GistHistory;
import ru.avystavkin.githubgists.content.GistHistoryChangeStatus;
import ru.avystavkin.githubgists.screen.base.holders.BaseGistViewHolder;
import ru.avystavkin.githubgists.utils.TextUtils;

public class GistDetailCommitViewHolder extends BaseGistViewHolder {

    @BindView(R.id.gist_commit_total)
    TextView mTotal;

    @BindView(R.id.gist_commit_additions)
    TextView mAdditions;

    @BindView(R.id.gist_commit_deletions)
    TextView mDeletions;

    public GistDetailCommitViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull List<GistHistory> list, int position) {
        if (position >= list.size())
            return;
        GistHistory history = list.get(position);
        super.bind(history.getUser());
        GistHistoryChangeStatus status = history.getChangeStatus();
        if (status != null) {
            mTotal.setText(String.format("%s: %d", AppDelegate.getContext().getResources().getString(R.string.commits_total), status.getTotal()));
            mAdditions.setText(String.format("%s: %d", AppDelegate.getContext().getResources().getString(R.string.commits_additions), status.getAdditions()));
            mDeletions.setText(String.format("%s: %d", AppDelegate.getContext().getResources().getString(R.string.commits_deletions), status.getDeletions()));
        }

        String dateStr = history.getCommittedAt();
        if (!TextUtils.isEmpty(dateStr)) {
            mName.setText(String.format("%s %s",
                    AppDelegate.getContext().getResources().getString(R.string.commits_date),
                    dateStr.replace("T", " ").replace("Z", ""))
            );
        }
    }
}
