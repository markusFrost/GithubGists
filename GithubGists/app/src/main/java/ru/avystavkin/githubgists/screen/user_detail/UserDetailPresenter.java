package ru.avystavkin.githubgists.screen.user_detail;

import android.content.Intent;
import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.screen.gist_detail.GistDetailActivity;

public class UserDetailPresenter {

    private final GithubRepository mRepository;
    private final LifecycleHandler mLifecycleHandler;
    private final UserView mView;

    public UserDetailPresenter(@NonNull GithubRepository repository, @NonNull LifecycleHandler lifecycleHandler,
                               @NonNull UserView view) {
        mRepository = repository;
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init(Intent intent) {
        if (intent == null)
            return;

        User user = new User();

        if (intent.hasExtra(GistDetailActivity.KEY_ID))
            user.setId(intent.getStringExtra(GistDetailActivity.KEY_ID));

        if (intent.hasExtra(GistDetailActivity.KEY_USER_NAME))
            user.setLogin(intent.getStringExtra(GistDetailActivity.KEY_USER_NAME));

        if (intent.hasExtra(GistDetailActivity.KEY_USER_URL))
            user.setAvatarUrl(intent.getStringExtra(GistDetailActivity.KEY_USER_URL));

        mView.showUser(user);
        loadUserGists(user.getId());
    }

    private void loadUserGists(String id) {
        //---temp
        id = "id";
        //---temp
    }
}
