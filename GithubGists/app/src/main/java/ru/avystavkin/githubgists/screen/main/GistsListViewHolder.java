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
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder;
import ru.avystavkin.githubgists.screen.interfaces.OnGistClickListner;
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener;
import ru.avystavkin.githubgists.screen.main.gist.GistsAdapter;
import ru.avystavkin.githubgists.widget.DividerItemDecoration;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;

public class GistsListViewHolder extends BaseViewHolder implements OnItemClickListener<Gist> {

    @BindView(R.id.recyclerViewVertical)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    @Nullable
    private OnGistClickListner mOnGistClickListener;

    private GistsAdapter mAdapter;

    public GistsListViewHolder(@Nullable OnGistClickListner onGistClickListener, View itemView) {
        super(itemView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(AppDelegate.getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(AppDelegate.getContext()));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new GistsAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        mOnGistClickListener = onGistClickListener;
    }

    public void bind(@NonNull List<Gist> gists) {
        mAdapter.changeDataSet(gists);
    }

    @Override
    public void onItemClick(@NonNull Gist gist) {
        if (mOnGistClickListener != null)
            mOnGistClickListener.onGistClick(gist);
    }
}
