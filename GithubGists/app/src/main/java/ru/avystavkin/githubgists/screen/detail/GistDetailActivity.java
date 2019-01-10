package ru.avystavkin.githubgists.screen.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.screen.general.LoadingDialog;
import ru.avystavkin.githubgists.screen.general.LoadingView;
import ru.avystavkin.githubgists.widget.DividerItemDecoration;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;

public class GistDetailActivity extends AppCompatActivity implements GistDetailView {

    public static final String KEY_NAME = "key_name";
    public static final String KEY_ID = "key_url";
    public static final String KEY_USER_NAME = "key_user_name";
    public static final String KEY_USER_URL = "key_user_url";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    private LoadingView mLoadingView;
    private GistDetailPresenter mPresenter;

    @Inject
    GithubRepository mRepository;

    private GistDetailAdapter mAdapter;

    public static void start(@NonNull Activity activity, Gist gist) {
        if (gist == null)
            return;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gists);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new GistDetailAdapter();
        mAdapter.attachToRecyclerView(mRecyclerView);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        AppDelegate.getAppComponent().injectGistDetailActivity(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mPresenter = new GistDetailPresenter(mRepository, lifecycleHandler, this);

        mPresenter.parseIfSuccessAndInit(getIntent());
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
