package ru.avystavkin.githubgists.screen.main;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.avystavkin.githubgists.repository.github.GithubRepository;

public class MainPagePresenter {

    private static final int POPULAR_COUNT = 10;

    private final GithubRepository mRepository;
    private final MainPageView mView;
    private final CompositeDisposable mCompositeDisposable;

    public MainPagePresenter(@NonNull GithubRepository repository,
                             @NonNull CompositeDisposable compositeDisposable,
                             @NonNull MainPageView view) {
        mRepository = repository;
        mView = view;
        mCompositeDisposable = compositeDisposable;
    }

    public void init() {
        Disposable disposable = mRepository.getGists()
                .doOnSubscribe(d -> mView.showLoading())
                .doOnError(mView::showError)
                .subscribe(gists -> {
                    mView.showGists(gists);
                    Disposable disposablePopularUsers = mRepository.getPopularUsers(POPULAR_COUNT)
                            .doAfterTerminate(mView::hideLoading)
                            .doOnError(mView::showError)
                            .subscribe(mView::showUsers,  mView::showError);
                    mCompositeDisposable.add(disposablePopularUsers);
                }, mView::showError);

        mCompositeDisposable.add(disposable);
    }
}
