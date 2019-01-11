package ru.avystavkin.githubgists.screen.user_detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity;
import ru.avystavkin.githubgists.screen.general.GistView;

public class UserDetailActivity extends BaseActivity implements GistView {

    @Inject
    GithubRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_gists);
        super.onCreate(savedInstanceState);

        AppDelegate.getAppComponent().injectUserDetailActivity(this);
    }

    public static void start(@NonNull Activity activity, User user) {
        Intent intent = new Intent(activity, UserDetailActivity.class);
        intent.putExtra(KEY_ID, user.getId());
            intent.putExtra(KEY_USER_NAME, user.getLogin());
            intent.putExtra(KEY_USER_URL, user.getAvatarUrl());
        activity.startActivity(intent);
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void showGist(@NonNull Object item) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
