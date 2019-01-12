package ru.avystavkin.githubgists.repository;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.avystavkin.githubgists.api.GithubService;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;

public class DefaultGithubRepository implements GithubRepository {

    private final GithubService mGithubService;

    public DefaultGithubRepository(@NonNull GithubService githubService) {
        mGithubService = githubService;
    }

    @NonNull
    @Override
    public Observable<List<Gist>> getGists() {
        return mGithubService.gists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    @Override
    public Observable<Gist> getGistById(String id) {
        return mGithubService.gist_detail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    @Override
    public Observable<List<Gist>> getGistsByUserName(String name) {
        return mGithubService.user_detail(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    @Override
    public Observable<List<GistHistory>> getCommitsByGistId(String id) {
        return mGithubService.gist_commits(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
