package ru.avystavkin.githubgists.screen.gist_detail;

import android.content.Intent;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.utils.TextUtils;

public class GistDetailPresenter {
    private final GithubRepository mRepository;
    private final GistView mView;

    public GistDetailPresenter(@NonNull GithubRepository repository,
                               @NonNull GistView view) {
        mRepository = repository;
        mView = view;
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

        Observable.merge(observableDetail, observableCommits)
                .doOnSubscribe(d -> mView.showLoading())
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showGist, mView::showError);
    }
}
