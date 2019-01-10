package ru.avystavkin.githubgists.screen.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.utils.TextUtils;

public class GistSectionHolder  extends RecyclerView.ViewHolder {

    @BindView(R.id.gist_section)
    protected TextView mName;

    public GistSectionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull String name) {
        if (!TextUtils.isEmpty(name)) {
            mName.setText(name);
        }
    }
}
