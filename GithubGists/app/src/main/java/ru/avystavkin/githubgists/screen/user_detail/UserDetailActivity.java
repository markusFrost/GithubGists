package ru.avystavkin.githubgists.screen.user_detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.AppDelegate;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.repository.github.GithubRepository;
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity;
import ru.avystavkin.githubgists.screen.gist_detail.GistDetailActivity;
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener;
import ru.avystavkin.githubgists.screen.main.gist.GistsAdapter;

public class UserDetailActivity extends BaseActivity implements UserView, OnItemClickListener<Gist> {

    @Inject
    GithubRepository mRepository;

    private UserDetailPresenter mPresenter;
    private GistsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_gists);
        super.onCreate(savedInstanceState);

        mAdapter = new GistsAdapter(new ArrayList<>());
        mAdapter.attachToRecyclerView(getMRecyclerView());
        mAdapter.setOnItemClickListener(this);

        AppDelegate.Companion.getAppComponent().injectUserDetailActivity(this);

        mPresenter = new UserDetailPresenter(mRepository, getCompositeDisposable(),this);
        mPresenter.init(getIntent());
    }

    public static void start(@NonNull Activity activity, User user) {
        Intent intent = new Intent(activity, UserDetailActivity.class);
        intent.putExtra(Companion.getKEY_ID(), user.getId());
            intent.putExtra(Companion.getKEY_USER_NAME(), user.getName());
            intent.putExtra(Companion.getKEY_USER_URL(), user.getUrl());
        activity.startActivity(intent);
    }

    @Override
    public void showError(Throwable throwable) {
        mAdapter.clear();
    }

    @Override
    public void showNoAccessNetworkMessage(Throwable throwable) {
        Toast.makeText(this, getResources().getString(R.string.no_network_access_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGists(@NonNull List<Gist> gists) {
        mAdapter.changeDataSet(gists);
    }

    @Override
    public void showLoading() {
        getMLoadingView().showLoading();
    }

    @Override
    public void hideLoading() {
        getMLoadingView().hideLoading();
    }

    @Override
    public void onItemClick(@NonNull Gist gist) {
        GistDetailActivity.start(this, gist);
    }
}
