package ru.avystavkin.githubgists.screen.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.server.GistServer;
import ru.avystavkin.githubgists.content.server.UserServer;
import ru.avystavkin.githubgists.screen.base.adapters.BaseRecyclerViewAdapter;
import ru.avystavkin.githubgists.screen.interfaces.OnGistClickListner;
import ru.avystavkin.githubgists.screen.interfaces.OnMainPageClickListner;
import ru.avystavkin.githubgists.screen.interfaces.OnUserClickListener;

public class MainPageAdapter extends BaseRecyclerViewAdapter implements OnGistClickListner, OnUserClickListener {

    private static final int HORIZONTAL_VIEW_TYPE = 0;
    private static final int VERTICAL_VIEW_TYPE = 1;

    private List<UserServer> mListUsers = new ArrayList<>();
    private List<GistServer> mLisGists = new ArrayList<>();

    @Nullable
    private OnMainPageClickListner mOnMainPageClickListner;

    public MainPageAdapter(@Nullable OnMainPageClickListner onMainPageClickListner) {
        mOnMainPageClickListner = onMainPageClickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HORIZONTAL_VIEW_TYPE) {
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
        if (position == HORIZONTAL_VIEW_TYPE)
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
        return  position;
    }

    public void clear() {
        mListUsers.clear();
        mLisGists.clear();

        refreshRecycler();
    }

    public void setListUsers(List<UserServer> listUsers) {
        mListUsers.clear();
        mListUsers.addAll(listUsers);
        refreshRecycler();
    }

    public void setListGists(List<GistServer> listGists) {
        mLisGists.clear();
        mLisGists.addAll(listGists);
        refreshRecycler();
    }

    @Override
    public void onGistClick(GistServer git) {
        if (mOnMainPageClickListner != null)
            mOnMainPageClickListner.onGistClick(git);
    }

    @Override
    public void onUserClick(UserServer user) {
        if (mOnMainPageClickListner != null)
            mOnMainPageClickListner.onUserClick(user);
    }
}
