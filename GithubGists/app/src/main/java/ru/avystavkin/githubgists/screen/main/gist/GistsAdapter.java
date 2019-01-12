package ru.avystavkin.githubgists.screen.main.gist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.server.GistServer;
import ru.avystavkin.githubgists.screen.base.adapters.BaseAdapter;

public class GistsAdapter extends BaseAdapter<GistViewHolder, GistServer> {

    public GistsAdapter(@NonNull List<GistServer> items) {
        super(items);
    }

    @Override
    public GistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GistViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gist, parent, false));
    }

    @Override
    public void onBindViewHolder(GistViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        GistServer gist = getItem(position);
        holder.bind(gist);
    }
}
