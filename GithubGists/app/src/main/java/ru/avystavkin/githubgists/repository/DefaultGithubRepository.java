package ru.avystavkin.githubgists.repository;

import android.support.annotation.NonNull;

import java.util.List;

import ru.arturvasilov.rxloader.RxUtils;
import ru.avystavkin.githubgists.api.GithubService;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;
import rx.Observable;

public class DefaultGithubRepository implements GithubRepository {

    private final GithubService mGithubService;

    public DefaultGithubRepository(@NonNull GithubService githubService) {
        mGithubService = githubService;
    }

    @NonNull
    @Override
    public Observable<List<Gist>> getGists() {
        return mGithubService.gists().compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<Gist> getGistById(String id) {
        return mGithubService.gist_detail(id).compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<List<Gist>> getGistsByUserName(String name) {
        return mGithubService.user_detail(name).compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<List<GistHistory>> getCommitsByGistId(String id) {
        return mGithubService.gist_commits(id).compose(RxUtils.async());
    }
}
