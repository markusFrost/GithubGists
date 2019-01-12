package ru.avystavkin.githubgists.screen.base.holders;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.models.local.Gist;

public abstract class BaseGistUserViewHolder extends BaseUserHolder {

    @BindView(R.id.gist_name)
    protected TextView mName;

    public BaseGistUserViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull Gist gist) {
        String gistName = gist.getName();
        if (!TextUtils.isEmpty(gistName))
            mName.setText(gistName);

        if (gist.getUser() != null){
           super.bind(gist.getUser());
        }
    }

    public TextView getName() {
        return mName;
    }

    protected void setName(@NonNull String text) {
        mName.setText(text);
    }
}
