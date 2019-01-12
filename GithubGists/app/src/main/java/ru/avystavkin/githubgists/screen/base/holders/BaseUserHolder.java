package ru.avystavkin.githubgists.screen.base.holders;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.utils.Images;

public abstract class BaseUserHolder extends BaseViewHolder {

    @BindView(R.id.gist_user_name)
    protected TextView mUserName;

    @BindView(R.id.gist_img)
    protected ImageView mImageView;

    public BaseUserHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull User user) {
        String userName = user.getName();
        if (!TextUtils.isEmpty(userName))
            mUserName.setText(userName);

        String url = user.getUrl();
        if (!TextUtils.isEmpty(url))
            Images.loadImage(mImageView, url);
    }
}
