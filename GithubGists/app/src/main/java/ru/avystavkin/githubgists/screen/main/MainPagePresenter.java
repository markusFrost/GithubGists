package ru.avystavkin.githubgists.screen.main;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.repository.GithubRepository;

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
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showGists, throwable -> mView.showError());

       mCompositeDisposable.add(disposable);
    }

    public void loadUsers(@NonNull List<Gist> gists) {
       //will work after db implement
        List<User> list = new ArrayList<>();
        for (int i = 0 ; i <= POPULAR_COUNT; i++) {
            User user = gists.get(i).getUser();
            if (user != null) {
                list.add(user);
            }
            mView.showUsers(list);
        }
    }
}
