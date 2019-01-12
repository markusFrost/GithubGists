package ru.avystavkin.githubgists.screen.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity;
import ru.avystavkin.githubgists.screen.gist_detail.GistDetailActivity;
import ru.avystavkin.githubgists.screen.interfaces.OnMainPageClickListner;
import ru.avystavkin.githubgists.screen.user_detail.UserDetailActivity;

public class MainPageActivity extends BaseActivity implements MainPageView, OnMainPageClickListner {

    private MainPagePresenter mPresenter;

    @Inject
    GithubRepository mRepository;

    private MainPageAdapter mAdapter;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, MainPageActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_gists);
        super.onCreate(savedInstanceState);


        mAdapter = new MainPageAdapter(this);
        mAdapter.attachToRecyclerView(mRecyclerView);

        AppDelegate.getAppComponent().injectGistActivity(this);

        mPresenter = new MainPagePresenter(mRepository, compositeDisposable, this);
        mPresenter.init();
    }

    @Override
    public void showGists(@NonNull List<Gist> gists) {
       mAdapter.setListGists(gists);
       mPresenter.loadUsers(gists);
    }

    @Override
    public void showUsers(@NonNull List<User> users) {
        mAdapter.setListUsers(users);
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
    public void showError() {
        mAdapter.clear();
    }

    @Override
    public void onGistClick(Gist gist) {
        GistDetailActivity.start(this, gist);
    }

    @Override
    public void onUserClick(User user) {
        UserDetailActivity.start(this, user);
    }
}
