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
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.screen.gists.detail.GistDetailActivity;
import ru.avystavkin.githubgists.screen.gists.main.GistsAdapter;
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener;
import ru.avystavkin.githubgists.widget.DividerItemDecoration;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;

public class VerticalRecyclerViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener<Gist> {

    @BindView(R.id.recyclerViewVertical)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    private GistsAdapter mAdapter;
    private Activity mActivity;

    public VerticalRecyclerViewHolder(Activity activity, View itemView) {
        super(itemView);
        mActivity = activity;
        ButterKnife.bind(this, itemView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(AppDelegate.getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(AppDelegate.getContext()));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new GistsAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);
    }

    public void bind(@NonNull List<Gist> gists) {
        mAdapter.changeDataSet(gists);
    }

    @Override
    public void onItemClick(@NonNull Gist item) {
        GistDetailActivity.start(mActivity, item);
    }
}
