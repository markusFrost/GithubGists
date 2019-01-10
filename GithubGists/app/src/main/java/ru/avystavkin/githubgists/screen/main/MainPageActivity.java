package ru.avystavkin.githubgists.screen.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
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
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.screen.detail.GistDetailActivity;
import ru.avystavkin.githubgists.screen.general.LoadingDialog;
import ru.avystavkin.githubgists.screen.general.LoadingView;
import ru.avystavkin.githubgists.screen.interfaces.OnMainPageClickListner;
import ru.avystavkin.githubgists.widget.DividerItemDecoration;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;
import rx.Observable;

public class MainPageActivity extends AppCompatActivity implements MainPageView, OnMainPageClickListner {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    View mEmptyView;

    private LoadingView mLoadingView;

    private MainPagePresenter mPresenter;

    private static final int POPULAR_COUNT = 10;

    @Inject
    GithubRepository mRepository;

    private MainPageAdapter mAdapter;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, MainPageActivity.class);
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

        mAdapter = new MainPageAdapter(this);
        mAdapter.attachToRecyclerView(mRecyclerView);

        AppDelegate.getAppComponent().injectGistActivity(this);
        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        mPresenter = new MainPagePresenter(mRepository, lifecycleHandler, this);
        mPresenter.init();
    }

    @Override
    public void showGists(@NonNull List<Gist> gists) {
       mAdapter.setListGists(gists);

        Observable.from(gists)
                .map(g -> g.getUser().getLogin())
                .groupBy(name -> name)
                .flatMap( gr -> gr.count().map(count -> new Pair<>(gr.getKey(), count)))
                .toSortedList((a, b) -> (-1) * a.second.compareTo(b.second))
                .flatMap(Observable::from)
                .take(POPULAR_COUNT)
                .map(pair -> {
                    User user = getUserByLogin(gists, pair.first);
                    user.setGistsCount(pair.second);
                    return user;
                })
                .toList()
                .subscribe( users -> mAdapter.setListUsers(users));
    }

    @Override
    public void showUsers(@NonNull List<User> users) {

    }

    private User getUserByLogin(List<Gist> list, String login) {
        for (Gist gist : list) {
            User user = gist.getUser();
            if (user != null && user.getLogin().equals(login))
                return user;
        }
        return new User();
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

    }
}
