package ru.avystavkin.githubgists.screen.main;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder;
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener;
import ru.avystavkin.githubgists.screen.interfaces.OnUserClickListener;
import ru.avystavkin.githubgists.screen.main.users.UserPopularAdapter;
import ru.avystavkin.githubgists.widget.DividerItemDecoration;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;

public class UsersListViewHolder extends BaseViewHolder implements OnItemClickListener<User> {

    @BindView(R.id.recyclerViewHorizontal)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    @Nullable
    private OnUserClickListener mOnUserClickListener;

    private UserPopularAdapter mAdapter;

    public UsersListViewHolder(@Nullable OnUserClickListener onUserClickListener, View itemView) {
        super(itemView);

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(AppDelegate.getContext(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(horizontalLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(AppDelegate.getContext()));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new UserPopularAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        mOnUserClickListener = onUserClickListener;
    }

    public void bind(@NonNull List<User> users) {
        mAdapter.changeDataSet(users);
    }

    @Override
    public void onItemClick(@NonNull User user) {
        if (mOnUserClickListener != null)
            mOnUserClickListener.onUserClick(user);
    }
}
