package ru.avystavkin.githubgists.screen.main.users;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import butterknife.BindView;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.server.UserServer;
import ru.avystavkin.githubgists.screen.base.holders.BaseViewHolder;
import ru.avystavkin.githubgists.utils.Images;

public class UserViewHolder extends BaseViewHolder {
    @BindView(R.id.user_name)
    protected TextView mUserName;

    @BindView(R.id.user_img)
    protected ImageView mImageView;

    public UserViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull UserServer user) {
        String name = user.getLogin();
        if (!TextUtils.isEmpty(name))
            mUserName.setText(String.format("%s ( %d )", name, user.getGistsCount()));

        String url = user.getAvatarUrl();
        if (!TextUtils.isEmpty(url))
            Images.loadImage(mImageView, url);
    }
}

