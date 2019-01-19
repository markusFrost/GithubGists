package ru.avystavkin.githubgists.screen.main.gist

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.screen.base.adapters.BaseAdapter

class GistsAdapter(items: List<Gist>) : BaseAdapter<GistViewHolder, Gist>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        return GistViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_gist, parent, false))
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }
}
