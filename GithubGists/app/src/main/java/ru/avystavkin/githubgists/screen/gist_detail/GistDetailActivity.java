package ru.avystavkin.githubgists.screen.gist_detail;

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
import ru.avystavkin.githubgists.models.local.GistCommit;
import ru.avystavkin.githubgists.repository.github.GithubRepository;
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity;

public class GistDetailActivity extends BaseActivity implements GistView {

    private GistDetailPresenter mPresenter;

    @Inject
    GithubRepository mRepository;

    private GistDetailAdapter mAdapter;

    public static void start(@NonNull Activity activity, @NonNull Gist gist) {
        Intent intent = new Intent(activity, GistDetailActivity.class);
        intent.putExtra(Companion.getKEY_NAME(), gist.getName());
        intent.putExtra(Companion.getKEY_ID(), gist.getId());
        intent.putExtra(Companion.getKEY_USER_NAME(), gist.getUser().getName());
        intent.putExtra(Companion.getKEY_USER_URL(), gist.getUser().getUrl());
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_gists);
        super.onCreate(savedInstanceState);

        mAdapter = new GistDetailAdapter();
        mAdapter.attachToRecyclerView(getMRecyclerView());

        AppDelegate.Companion.getAppComponent().injectGistDetailActivity(this);

        mPresenter = new GistDetailPresenter(mRepository, getCompositeDisposable(),this);
        mPresenter.init(getIntent());
    }

    @Override
    public void showGist(@NonNull Object item) {
        if (item instanceof Gist)
            mAdapter.setGist((Gist) item);
        else if (item instanceof List<?>)
            mAdapter.setCommits((List<GistCommit>) item);
    }

    @Override
    public void showError(Throwable throwable) {
        System.out.println(throwable.toString());
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
    public void showNoAccessNetworkMessage(Throwable throwable) {
        Toast.makeText(this, getResources().getString(R.string.no_network_access_message), Toast.LENGTH_SHORT).show();
    }
}
