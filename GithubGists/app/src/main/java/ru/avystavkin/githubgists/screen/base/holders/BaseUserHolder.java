package ru.avystavkin.githubgists.screen.base.holders;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.User;
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
        String userName = user.getLogin();
        if (!TextUtils.isEmpty(userName))
            mUserName.setText(userName);

        String url = user.getAvatarUrl();
        if (!TextUtils.isEmpty(url))
            Images.loadImage(mImageView, url);
    }
}
