package ru.avystavkin.githubgists.screen.base.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;

public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @Nullable
    private EmptyRecyclerView mRecyclerView;

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
}
