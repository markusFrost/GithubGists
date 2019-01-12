package ru.avystavkin.githubgists.screen.gist_detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity;

public class GistDetailActivity extends BaseActivity implements GistView {

    private GistDetailPresenter mPresenter;

    @Inject
    GithubRepository mRepository;

    private GistDetailAdapter mAdapter;

    public static void start(@NonNull Activity activity, @NonNull Gist gist) {
        Intent intent = new Intent(activity, GistDetailActivity.class);
        intent.putExtra(KEY_NAME, gist.getName());
        intent.putExtra(KEY_ID, gist.getId());
        if (gist.getUser() != null) {
            intent.putExtra(KEY_USER_NAME, gist.getUser().getLogin());
            intent.putExtra(KEY_USER_URL, gist.getUser().getAvatarUrl());
        }
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_gists);
        super.onCreate(savedInstanceState);

        mAdapter = new GistDetailAdapter();
        mAdapter.attachToRecyclerView(mRecyclerView);

        AppDelegate.getAppComponent().injectGistDetailActivity(this);

        mPresenter = new GistDetailPresenter(mRepository, mLifecycleHandler, this);
        mPresenter.loadGistInfo(getIntent());
    }

    @Override
    public void showGist(@NonNull Object item) {
        if (item instanceof Gist)
            mAdapter.setGist((Gist) item);
        else if (item instanceof List<?>)
            mAdapter.setCommits((List<GistHistory>) item);
    }

    @Override
    public void showError(Throwable throwable) {
        System.out.println(throwable.toString());
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
