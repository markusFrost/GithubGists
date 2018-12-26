package ru.avystavkin.githubgists.screen.gists.main;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.repository.GithubRepository;

public class GistsPresenter {

    private final GithubRepository mRepository;
    private final LifecycleHandler mLifecycleHandler;
    private final GistsView mView;

    public GistsPresenter(@NonNull GithubRepository repository, @NonNull LifecycleHandler lifecycleHandler,
                                 @NonNull GistsView view) {
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
