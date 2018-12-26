package ru.avystavkin.githubgists.screen.gists.main;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.screen.gists.holders.GistHolder;
import ru.avystavkin.githubgists.widget.BaseAdapter;

public class GistsAdapter extends BaseAdapter<GistHolder, Gist> {

    public GistsAdapter(@NonNull List<Gist> items) {
        super(items);
    }

    @Override
    public GistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GistHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gist, parent, false));
    }

    @Override
    public void onBindViewHolder(GistHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Gist gist = getItem(position);
        holder.bind(gist);
    }
}
