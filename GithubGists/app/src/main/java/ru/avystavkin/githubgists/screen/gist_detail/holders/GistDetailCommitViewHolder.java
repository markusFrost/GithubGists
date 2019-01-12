package ru.avystavkin.githubgists.screen.gist_detail.holders;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.server.GistHistoryServer;
import ru.avystavkin.githubgists.content.server.GistHistoryChangeStatusServer;
import ru.avystavkin.githubgists.screen.base.holders.BaseGistUserViewHolder;

public class GistDetailCommitViewHolder extends BaseGistUserViewHolder {

    @BindView(R.id.gist_commit_total)
    TextView mTotal;

    @BindView(R.id.gist_commit_additions)
    TextView mAdditions;

    @BindView(R.id.gist_commit_deletions)
    TextView mDeletions;

    public GistDetailCommitViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull List<GistHistoryServer> list, int position) {
        if (position >= list.size())
            return;
        GistHistoryServer history = list.get(position);
        super.bind(history.getUser());
        GistHistoryChangeStatusServer status = history.getChangeStatus();
        if (status != null) {
            mTotal.setText(String.format("%s: %d", AppDelegate.getContext().getResources().getString(R.string.commits_total), status.getTotal()));
            mAdditions.setText(String.format("%s: %d", AppDelegate.getContext().getResources().getString(R.string.commits_additions), status.getAdditions()));
            mDeletions.setText(String.format("%s: %d", AppDelegate.getContext().getResources().getString(R.string.commits_deletions), status.getDeletions()));
        }

        String dateStr = history.getCommittedAt();
        if (!TextUtils.isEmpty(dateStr)) {
            setName(String.format("%s %s",
                    AppDelegate.getContext().getResources().getString(R.string.commits_date),
                    dateStr.replace("T", " ").replace("Z", ""))
            );
        }
    }
}
