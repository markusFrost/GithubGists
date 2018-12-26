package ru.avystavkin.githubgists.screen.gists.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.screen.general.LoadingDialog;
import ru.avystavkin.githubgists.screen.general.LoadingView;
import ru.avystavkin.githubgists.screen.gists.detail.GistDetailActivity;
import ru.avystavkin.githubgists.widget.BaseAdapter;
import ru.avystavkin.githubgists.widget.DividerItemDecoration;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;

public class GistsActivity extends AppCompatActivity implements GistsView,
        BaseAdapter.OnItemClickListener<Gist> {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    private LoadingView mLoadingView;

    private GistsPresenter mPresenter;

    @Inject
    GithubRepository mRepository;

    private GistsAdapter mAdapter;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, GistsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gists);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);

        mAdapter = new GistsAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        AppDelegate.getAppComponent().injectGistActivity(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mPresenter = new GistsPresenter(mRepository, lifecycleHandler, this);
        mPresenter.init();
    }

    @Override
    public void onItemClick(@NonNull Gist item) {
        mPresenter.onItemClick(item);
    }

    @Override
    public void showGists(@NonNull List<Gist> gists) {
       mAdapter.changeDataSet(gists);
    }

    private User getUserByLogin(List<Gist> list, String login) {
        for (Gist gist : list) {
            User user = gist.getUser();
            if (user != null && user.getAvatarUrl().equals(login))
                return user;
        }
        return new User();
    }

    @Override
    public void showGistDetail(Gist gist) {
        GistDetailActivity.start(this, gist);
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

}
