package ru.avystavkin.githubgists.screen.main;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.avystavkin.githubgists.database.DbHelper;
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
                .doOnSubscribe(d -> mView.showLoading())
                .doOnError(throwable ->  {
                    mView.showNoAccessNetworkMessage(throwable);
                    mView.hideLoading();
                })
                .observeOn(Schedulers.io())
                .flatMap(list -> {
                    mDbHelper.insert(list);
                    return Observable.fromArray(list);
                })
                .onErrorReturn(throwable -> mDbHelper.getGists())
                .compose(RxUtils.async())
                .subscribe(gists -> {
                    mView.showGists(gists);
                    Disposable disposablePopularUsers = Observable.just(true)
                            .observeOn(AndroidSchedulers.mainThread())
                            .doAfterTerminate(mView::hideLoading)
                            .doOnError(throwable ->  mView.hideLoading())
                            .observeOn(Schedulers.io())
                            .flatMap(x -> Observable.fromArray(mDbHelper.getPopularUsers(POPULAR_COUNT)))
                            .compose(RxUtils.async())
                            .subscribe(mView::showUsers,  mView::showError);
                    mCompositeDisposable.add(disposablePopularUsers);
                }, mView::showError);

        mCompositeDisposable.add(disposable);
    }
}
