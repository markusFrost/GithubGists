package ru.avystavkin.githubgists.screen.user_detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.screen.base.adapters.BaseRecyclerViewAdapter;
import ru.avystavkin.githubgists.screen.main.gist.GistViewHolder;

public class UserDetailAdapter extends BaseRecyclerViewAdapter {

    private static final int GISTS_LIST_TYPE = 1;

    private @NonNull
    List<Gist> mGists = new ArrayList<>();

    public void setGists(@NonNull List<Gist> gists) {
        mGists.clear();
        mGists.addAll(gists);
        refreshRecycler();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case GISTS_LIST_TYPE: {
                holder = new GistViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_gist, parent, false));
            }break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case GISTS_LIST_TYPE: {
                GistViewHolder gistViewHolder = (GistViewHolder) holder;
                gistViewHolder.bind(mGists.get(position - 1));
            }break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return GISTS_LIST_TYPE;
    }

    @Override
    public int getItemCount() {
        return mGists.size();
    }
}
