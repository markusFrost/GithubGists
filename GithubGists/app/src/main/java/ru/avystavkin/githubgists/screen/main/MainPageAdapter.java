package ru.avystavkin.githubgists.screen.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.screen.holders.HorizontalRecyclerViewHolder;
import ru.avystavkin.githubgists.screen.holders.VerticalRecyclerViewHolder;
import ru.avystavkin.githubgists.screen.interfaces.OnGistClickListner;
import ru.avystavkin.githubgists.screen.interfaces.OnMainPageClickListner;
import ru.avystavkin.githubgists.screen.interfaces.OnUserClickListener;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;

public class MainPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnGistClickListner, OnUserClickListener {

    private static final int HORIZONTAL_VIEW_TYPE = 0;
    private static final int VERTCAL_VIEW_TYPE = 1;

    private List<User> mListUsers;
    private List<Gist> mLisGists;

    @Nullable
    private OnMainPageClickListner mOnMainPageClickListner;

    @Nullable
    private EmptyRecyclerView mRecyclerView;

    public MainPageAdapter(@Nullable OnMainPageClickListner onMainPageClickListner) {
        mOnMainPageClickListner = onMainPageClickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HORIZONTAL_VIEW_TYPE) {
            return new HorizontalRecyclerViewHolder(this, LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recylerview_horizontal, parent, false));
        }
        else {
            return new VerticalRecyclerViewHolder(this, LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recylerview_vertical, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == HORIZONTAL_VIEW_TYPE)
            ((HorizontalRecyclerViewHolder)holder).bind(mListUsers);
        else
            ((VerticalRecyclerViewHolder)holder).bind(mLisGists);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mLisGists != null && mLisGists.size() > 0)
            count++;

        if (mListUsers != null && mListUsers.size() > 0)
            count++;

        return count;
    }

    @Override
    public int getItemViewType(int position) {
        return  position;
    }

    public void clear() {
        if (mListUsers != null)
            mListUsers.clear();

        if (mLisGists != null)
            mLisGists.clear();

        refreshRecycler();
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

    public void setListUsers(List<User> listUsers) {
        this.mListUsers = listUsers;
        refreshRecycler();
    }

    public void setListGists(List<Gist> listGists) {
        this.mLisGists = listGists;
        refreshRecycler();
    }

    @Override
    public void onGistClick(Gist git) {
        if (mOnMainPageClickListner != null)
            mOnMainPageClickListner.onGistClick(git);
    }

    @Override
    public void onUserClick(User user) {
        if (mOnMainPageClickListner != null)
            mOnMainPageClickListner.onUserClick(user);
    }
}
