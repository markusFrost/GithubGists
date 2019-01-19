package ru.avystavkin.githubgists.screen.gist_detail;

import android.content.Intent;
import android.text.TextUtils;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.GistCommit;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.repository.github.GithubRepository;

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

    public void init(Intent intent) {
        if (intent == null)
            return;

        Gist gist = new Gist();
        gist.setUser(new User());

        if (intent.hasExtra(GistDetailActivity.Companion.getKEY_NAME()))
            gist.setName(intent.getStringExtra(GistDetailActivity.Companion.getKEY_NAME()));

        if (intent.hasExtra(GistDetailActivity.Companion.getKEY_ID()))
            gist.setId(intent.getStringExtra(GistDetailActivity.Companion.getKEY_ID()));

        if (intent.hasExtra(GistDetailActivity.Companion.getKEY_USER_NAME()))
            gist.getUser().setName(intent.getStringExtra(GistDetailActivity.Companion.getKEY_USER_NAME()));

        if (intent.hasExtra(GistDetailActivity.Companion.getKEY_USER_URL()))
            gist.getUser().setUrl(intent.getStringExtra(GistDetailActivity.Companion.getKEY_USER_URL()));

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

        Observable<Gist> observableDetail = mRepository.getGistById(id);
        Observable<List<GistCommit>> observableCommits = mRepository.getCommitsByGistId(id);

       Disposable disposable = Observable.merge(observableDetail, observableCommits)
                .doOnSubscribe(d -> mView.showLoading())
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showGist, mView::showError);

       mCompositeDisposable.add(disposable);
    }
}
