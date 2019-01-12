package ru.avystavkin.githubgists.screen.user_detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.server.GistServer;
import ru.avystavkin.githubgists.content.server.UserServer;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity;
import ru.avystavkin.githubgists.screen.gist_detail.GistDetailActivity;
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener;
import ru.avystavkin.githubgists.screen.main.gist.GistsAdapter;

public class UserDetailActivity extends BaseActivity implements UserView, OnItemClickListener<GistServer> {

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
        mAdapter.setOnItemClickListener(this);

        AppDelegate.getAppComponent().injectUserDetailActivity(this);

        mPresenter = new UserDetailPresenter(mRepository, compositeDisposable,this);
        mPresenter.init(getIntent());
    }

    public static void start(@NonNull Activity activity, UserServer user) {
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
    public void showGists(@NonNull List<GistServer> gists) {
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

    @Override
    public void onItemClick(@NonNull GistServer gist) {
        GistDetailActivity.start(this, gist);
    }
}
