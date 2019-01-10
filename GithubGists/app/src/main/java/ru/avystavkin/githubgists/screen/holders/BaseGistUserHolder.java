package ru.avystavkin.githubgists.screen.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.utils.Images;
import ru.avystavkin.githubgists.utils.TextUtils;

public abstract class BaseGistUserHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.gist_name)
    protected TextView mName;

    @BindView(R.id.gist_user_name)
    protected TextView mUserName;

    @BindView(R.id.gist_img)
    protected ImageView mImageView;

    public BaseGistUserHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Gist gist) {
        if (gist != null) {
            String gistName = gist.getName();
            if (!TextUtils.isEmpty(gistName))
                mName.setText(gistName);

            if (gist.getUser() != null){
                String userName = gist.getUser().getLogin();
                if (!TextUtils.isEmpty(userName))
                    mUserName.setText(userName);
            }

            Images.loadGistAvatar(mImageView, gist);
        }
    }

    public void bind(@NonNull User user) {
        if (user != null) {
            String userName = user.getLogin();
            if (!TextUtils.isEmpty(userName))
                mUserName.setText(userName);

            String url = user.getAvatarUrl();
            if (!TextUtils.isEmpty(url))
                Images.loadImage(mImageView, url);

            mName.setText("");
        }
    }

    public TextView getName() {
        return mName;
    }

    public void setName(TextView name) {
        this.mName = name;
    }

    public TextView getUserName() {
        return mUserName;
    }

    public void setUserName(TextView userName) {
        this.mUserName = userName;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public void setImageView(ImageView imageView) {
        this.mImageView = imageView;
    }
}
