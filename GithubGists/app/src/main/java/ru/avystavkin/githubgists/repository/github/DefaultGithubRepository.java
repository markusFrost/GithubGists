package ru.avystavkin.githubgists.repository.github;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import ru.avystavkin.githubgists.api.GithubService;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.GistCommit;
import ru.avystavkin.githubgists.repository.github.transformer.RxGistCommitTransformer;
import ru.avystavkin.githubgists.repository.github.transformer.RxGistTransformer;
import ru.avystavkin.githubgists.repository.github.transformer.RxGistsListTransformer;
import ru.avystavkin.githubgists.utils.RxUtils;

public class DefaultGithubRepository implements GithubRepository {

    private final GithubService mGithubService;

    public DefaultGithubRepository(@NonNull GithubService githubService) {
        mGithubService = githubService;
    }

    @NonNull
    @Override
    public Observable<List<Gist>> getGists() {
         return mGithubService.gists()
                .compose(new RxGistsListTransformer())
                .compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<Gist> getGistById(String id) {
        return mGithubService.gist_detail(id)
                .compose(new RxGistTransformer())
                .compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<List<Gist>> getGistsByUserName(String name) {
        return mGithubService.user_detail(name)
                .compose(new RxGistsListTransformer())
                .compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<List<GistCommit>> getCommitsByGistId(String id) {
        return mGithubService.gist_commits(id)
                .compose(new RxGistCommitTransformer())
                .compose(RxUtils.async());
    }
}
