package ru.avystavkin.githubgists.screen.base.holders;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
