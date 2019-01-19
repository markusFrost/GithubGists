package ru.avystavkin.githubgists.screen.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

        AppDelegate.Companion.getAppComponent().injectGistActivity(this);

        mAdapter = new MainPageAdapter(this);
        mAdapter.attachToRecyclerView(getMRecyclerView());

        mPresenter = new MainPagePresenter(mRepository, getCompositeDisposable(), this);
        mPresenter.init();
    }


    @Override
    public void showGists(@NonNull List<Gist> gists) {
       mAdapter.setListGists(gists);
    }

    @Override
    public void showUsers(@NonNull List<User> users) {
        mAdapter.setListUsers(users);
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
    public void showError(Throwable throwable) {

    }

    @Override
    public void showNoAccessNetworkMessage(Throwable throwable) {
        Toast.makeText(this, getResources().getString(R.string.no_network_access_message), Toast.LENGTH_LONG).show();
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
