package ru.avystavkin.githubgists.screen.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.utils.Images;
import ru.avystavkin.githubgists.utils.TextUtils;

public class UserPopularHolder  extends RecyclerView.ViewHolder {
    @BindView(R.id.user_name)
    protected TextView mUserName;

    @BindView(R.id.user_img)
    protected ImageView mImageView;

    public UserPopularHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull User user) {
        String name = user.getLogin();
        if (!TextUtils.isEmpty(name))
            mUserName.setText(name + "( " + user.getGistsCount() + " )");

        String url = user.getAvatarUrl();
        if (!TextUtils.isEmpty(url))
            Images.loadImage(mImageView, url);
    }
}

