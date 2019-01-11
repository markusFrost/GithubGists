package ru.avystavkin.githubgists.screen.detail.holders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder;
import ru.avystavkin.githubgists.utils.TextUtils;

public class GistSectionHolder  extends BaseViewHolder {

    @BindView(R.id.gist_section)
    protected TextView mName;

    public GistSectionHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull String name) {
        if (!TextUtils.isEmpty(name)) {
            mName.setText(name);
        }
    }
}
