package ru.avystavkin.githubgists.screen.gists.holders;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.screen.gists.popular.UserPopularAdapter;
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener;
import ru.avystavkin.githubgists.widget.DividerItemDecoration;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;

public class HorizontalRecyclerViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener<User> {

    @BindView(R.id.recyclerViewHorizontal)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    private Activity mActivity;

    private UserPopularAdapter mAdapter;

    public HorizontalRecyclerViewHolder(Activity activity, View itemView) {
        super(itemView);
        mActivity = activity;
        ButterKnife.bind(this, itemView);

        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(AppDelegate.getContext(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(horizontalLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(AppDelegate.getContext()));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new UserPopularAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);

    }

    public void bind(@NonNull List<User> users) {
        mAdapter.changeDataSet(users);
    }

    @Override
    public void onItemClick(@NonNull User item) {

    }
}
