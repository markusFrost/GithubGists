package ru.avystavkin.githubgists.screen.gist_detail;

import android.content.Intent;
import android.text.TextUtils;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;

public class GistDetailPresenter {
    private final GithubRepository mRepository;
    private final GistView mView;
    private final CompositeDisposable mCompositeDisposable;

    public GistDetailPresenter(@NonNull GithubRepository repository,
                               @NonNull CompositeDisposable compositeDisposable,
                               @NonNull GistView view) {
        mRepository = repository;
        mView = view;
        mCompositeDisposable = compositeDisposable;
    }

    public void loadGistInfo(Intent intent) {
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
        loadGistInfo(gist.getId());
    }

    public void loadGistInfo(String id) {
        //---temp
        id = "id";
        //---temp
        if (TextUtils.isEmpty(id)) {
            return;
        }

        Observable<Gist> observableDetail = mRepository.getGistById(id);
        Observable<List<GistHistory>> observableCommits = mRepository.getCommitsByGistId(id);

       Disposable disposable = Observable.merge(observableDetail, observableCommits)
                .doOnSubscribe(d -> mView.showLoading())
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showGist, mView::showError);

       mCompositeDisposable.add(disposable);
    }
}
