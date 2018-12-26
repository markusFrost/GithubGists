package ru.avystavkin.githubgists.screen.gists.detail;

import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.utils.TextUtils;
import rx.Observable;

public class GistDetailPresenter {
    private final GithubRepository mRepository;
    private final LifecycleHandler mLifecycleHandler;
    private final GistDetailView mView;

    public GistDetailPresenter(@NonNull GithubRepository repository, @NonNull LifecycleHandler lifecycleHandler,
                          @NonNull GistDetailView view) {
        mRepository = repository;
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void parseIfSuccessAndInit(Intent intent) {
        if (intent == null)
            return;

        Gist gist = new Gist();
        gist.setUser(new User());

        if (intent.hasExtra(GistDetailActivity.KEY_NAME))
            gist.setDescription(intent.getStringExtra(GistDetailActivity.KEY_NAME));

        if (intent.hasExtra(GistDetailActivity.KEY_ID))
            gist.setId(intent.getStringExtra(GistDetailActivity.KEY_ID));

        if (intent.hasExtra(GistDetailActivity.KEY_USER_NAME))
            gist.getUser().setLogin(intent.getStringExtra(GistDetailActivity.KEY_USER_NAME));

        if (intent.hasExtra(GistDetailActivity.KEY_USER_URL))
            gist.getUser().setAvatarUrl(intent.getStringExtra(GistDetailActivity.KEY_USER_URL));

        mView.showGist(gist);
        init(gist.getId());
    }

    public void init(String id) {
//        //---temp
//        id = "id";
//        //---temp
        if (TextUtils.isEmpty(id)) {
            return;
        }

        Observable<Gist> observableDetail = mRepository.gist_detail(id);
        Observable<List<GistHistory>> observableCommits = mRepository.get_gist_commits(id);

        Observable.merge(observableDetail, observableCommits)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.gists_request))
                .subscribe(mView::showGist, throwable -> mView.showError(throwable));
    }
}
