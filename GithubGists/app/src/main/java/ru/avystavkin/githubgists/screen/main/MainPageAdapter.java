package ru.avystavkin.githubgists.screen.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.screen.base.adapters.BaseRecyclerViewAdapter;
import ru.avystavkin.githubgists.screen.interfaces.OnGistClickListner;
import ru.avystavkin.githubgists.screen.interfaces.OnMainPageClickListner;
import ru.avystavkin.githubgists.screen.interfaces.OnUserClickListener;

public class MainPageAdapter extends BaseRecyclerViewAdapter implements OnGistClickListner, OnUserClickListener {

    private static final int USERS_LIST_VIEW_TYPE = 0;
    private static final int GISTS_LIST_VIEW_TYPE = 1;

    private List<User> mListUsers = new ArrayList<>();
    private List<Gist> mLisGists = new ArrayList<>();

    @Nullable
    private OnMainPageClickListner mOnMainPageClickListner;

    public MainPageAdapter(@Nullable OnMainPageClickListner onMainPageClickListner) {
        mOnMainPageClickListner = onMainPageClickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == USERS_LIST_VIEW_TYPE) {
            return new UsersListViewHolder(this, LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recylerview_horizontal, parent, false));
        }
        else {
            return new GistsListViewHolder(this, LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recylerview_vertical, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == USERS_LIST_VIEW_TYPE)
            ((UsersListViewHolder)holder).bind(mListUsers);
        else
            ((GistsListViewHolder)holder).bind(mLisGists);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mLisGists.size() > 0)
            count++;

        if (mListUsers.size() > 0)
            count++;

        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            if (mListUsers.size() > 0)
                return USERS_LIST_VIEW_TYPE;
            else
                return GISTS_LIST_VIEW_TYPE;
        }
        else if (position == 1)
            return GISTS_LIST_VIEW_TYPE;
        return  position;
    }

    public void clear() {
        mListUsers.clear();
        mLisGists.clear();

        refreshRecycler();
    }

    public void setListUsers(List<User> listUsers) {
        mListUsers.clear();
        mListUsers.addAll(listUsers);
        refreshRecycler();
    }

    public void setListGists(List<Gist> listGists) {
        mLisGists.clear();
        mLisGists.addAll(listGists);
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
