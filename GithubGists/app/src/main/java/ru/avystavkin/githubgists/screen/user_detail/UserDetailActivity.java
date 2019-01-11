package ru.avystavkin.githubgists.screen.user_detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.screen.general.GistView;

public class UserDetailActivity extends AppCompatActivity implements GistView {

    public static final String KEY_NAME = "key_name";
    public static final String KEY_ID = "key_url";
    public static final String KEY_USER_NAME = "key_user_name";
    public static final String KEY_USER_URL = "key_user_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
