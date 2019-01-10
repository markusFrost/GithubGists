package ru.avystavkin.githubgists.screen.main;

import android.app.Activity;
import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;

public class MainPagePresenter {

    private final GithubRepository mRepository;
    private final LifecycleHandler mLifecycleHandler;
    private final MainPageView mView;

    public MainPagePresenter(@NonNull GithubRepository repository, @NonNull LifecycleHandler lifecycleHandler,
                             @NonNull MainPageView view) {
        mRepository = repository;
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init() {
        mRepository.getGists()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.gists_request))
                .subscribe(mView::showGists, throwable -> mView.showError());
    }
}
