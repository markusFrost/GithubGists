package ru.avystavkin.githubgists.screen.user_detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity;
import ru.avystavkin.githubgists.screen.main.gist.GistsAdapter;

public class UserDetailActivity extends BaseActivity implements UserView {

    @Inject
    GithubRepository mRepository;

    private UserDetailPresenter mPresenter;
    private GistsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_gists);
        super.onCreate(savedInstanceState);

        mAdapter = new GistsAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);

        AppDelegate.getAppComponent().injectUserDetailActivity(this);

        mPresenter = new UserDetailPresenter(mRepository, mLifecycleHandler, this);
        mPresenter.init(getIntent());
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
        mAdapter.clear();
    }

    @Override
    public void showGists(@NonNull List<Gist> gists) {
        mAdapter.changeDataSet(gists);
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

}
