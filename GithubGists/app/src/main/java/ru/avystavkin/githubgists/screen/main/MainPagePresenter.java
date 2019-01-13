package ru.avystavkin.githubgists.screen.main;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.avystavkin.githubgists.database.DbHelper;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.repository.github.GithubRepository;
import ru.avystavkin.githubgists.utils.RxUtils;

public class MainPagePresenter {

    private static final int POPULAR_COUNT = 10;

    private final GithubRepository mRepository;
    private final MainPageView mView;
    private final CompositeDisposable mCompositeDisposable;
    private final DbHelper mDbHelper;

    public MainPagePresenter(@NonNull GithubRepository repository,
                             @NonNull DbHelper dbHelper,
                             @NonNull CompositeDisposable compositeDisposable,
                             @NonNull MainPageView view) {
        mRepository = repository;
        mView = view;
        mCompositeDisposable = compositeDisposable;
        this.mDbHelper = dbHelper;
    }

    public void init() {
        Disposable disposable = mRepository.getGists()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(mView::showNoAccessNetworkMessage)
                .doOnSubscribe(d -> mView.showLoading())
                .doAfterTerminate(mView::hideLoading)
                .observeOn(Schedulers.io())
                .flatMap(list -> {
                    mDbHelper.insert(list);
                    return Observable.fromArray(list);
                })
                .onErrorReturn(throwable -> mDbHelper.getGists())
                .compose(RxUtils.async())
                .subscribe(mView::showGists, mView::showError);

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
