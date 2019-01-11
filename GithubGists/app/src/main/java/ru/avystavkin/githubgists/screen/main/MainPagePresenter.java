package ru.avystavkin.githubgists.screen.main;

import android.support.annotation.NonNull;
import android.util.Pair;

import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.RxUtils;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.utils.TextUtils;
import rx.Observable;

public class MainPagePresenter {

    private static final int POPULAR_COUNT = 10;

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

    public void loadUsers(@NonNull List<Gist> gists) {
        Observable.from(gists)
                .compose(RxUtils.async())
                .filter(gist -> gist.getUser() != null && !TextUtils.isEmpty(gist.getUser().getLogin()))
                .map(g -> g.getUser().getLogin())
                .groupBy(login -> login)
                .flatMap( gr -> gr.count().map(count -> new Pair<>(gr.getKey(), count)))
                .toSortedList((p1, p2) -> (-1) * p1.second.compareTo(p2.second))
                .flatMap(Observable::from)
                .take(POPULAR_COUNT)
                .map(pair -> {
                    User user = getUserByLogin(gists, pair.first);
                    user.setGistsCount(pair.second);
                    return user;
                })
                .toList()
                .subscribe(mView::showUsers);
    }

    private User getUserByLogin(List<Gist> list, String login) {
        for (Gist gist : list) {
            User user = gist.getUser();
            if (user != null && user.getLogin().equals(login))
                return user;
        }
        return new User();
    }
}
