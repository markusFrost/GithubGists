package ru.avystavkin.githubgists.screen.gist_detail.holders;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder;

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
