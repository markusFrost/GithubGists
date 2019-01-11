package ru.avystavkin.githubgists.screen.holders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.GistHistory;
import ru.avystavkin.githubgists.content.GistHistoryChangeStatus;
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
            mTotal.setText("total: " +  status.getTotal());
            mAdditions.setText("additions: " + status.getAdditions());
            mDeletions.setText("deletions: " +   status.getDeletions());
        }

        String dateStr = history.getCommittedAt();
        if (!TextUtils.isEmpty(dateStr)) {
            mName.setText("commited at " + dateStr.replace("T", " ").replace("Z", ""));
        }
    }
}
