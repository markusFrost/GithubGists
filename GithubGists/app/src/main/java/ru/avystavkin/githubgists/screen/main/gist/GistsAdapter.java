package ru.avystavkin.githubgists.screen.main.gist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.screen.base.adapters.BaseAdapter;

public class GistsAdapter extends BaseAdapter<GistViewHolder, Gist> {

    public GistsAdapter(@NonNull List<Gist> items) {
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
        Gist gist = getItem(position);
        holder.bind(gist);
    }
}
