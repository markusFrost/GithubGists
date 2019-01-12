package ru.avystavkin.githubgists.repository;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import ru.avystavkin.githubgists.api.GithubService;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;
import ru.avystavkin.githubgists.utils.RxUtils;

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
