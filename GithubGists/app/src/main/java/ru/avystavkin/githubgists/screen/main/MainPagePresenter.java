package ru.avystavkin.githubgists.screen.main;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.avystavkin.githubgists.database.AppDatabase;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.repository.github.GithubRepository;

public class MainPagePresenter {

    private static final int POPULAR_COUNT = 10;

    private final GithubRepository mRepository;
    private final MainPageView mView;
    private final CompositeDisposable mCompositeDisposable;
    private final AppDatabase mAppDatabase;

    public MainPagePresenter(@NonNull GithubRepository repository,
                             @NonNull AppDatabase appDatabase,
                             @NonNull CompositeDisposable compositeDisposable,
                             @NonNull MainPageView view) {
        mRepository = repository;
        mView = view;
        mCompositeDisposable = compositeDisposable;
        this.mAppDatabase = appDatabase;
    }

    public void init() {
       Disposable disposable = mRepository.getGists()
                .doOnSubscribe(d -> mView.showLoading())
                .doOnTerminate(mView::hideLoading)
                //.subscribe(mView::showGists, throwable -> mView.showError());
        .subscribe(gists -> {
            mView.showGists(gists);
            for(Gist gist : gists)
                mAppDatabase.gistDao().insert(gist);
        }, throwable -> mView.showError());//todo - if error show in db but notify

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
