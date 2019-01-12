package ru.avystavkin.githubgists.repository;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import ru.avystavkin.githubgists.api.GithubService;
import ru.avystavkin.githubgists.content.server.GistHistoryServer;
import ru.avystavkin.githubgists.content.server.GistServer;
import ru.avystavkin.githubgists.utils.RxUtils;

public class DefaultGithubRepository implements GithubRepository {

    private final GithubService mGithubService;

    public DefaultGithubRepository(@NonNull GithubService githubService) {
        mGithubService = githubService;
    }

    @NonNull
    @Override
    public Observable<List<GistServer>> getGists() {
//         return mGithubService.gists()
//                .compose(new RxGistTransformer())
//                .compose(RxUtils.async());

        return null;
    }

    @NonNull
    @Override
    public Observable<GistServer> getGistById(String id) {
        return mGithubService.gist_detail(id).compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<List<GistServer>> getGistsByUserName(String name) {
        return mGithubService.user_detail(name).compose(RxUtils.async());
    }

    @NonNull
    @Override
    public Observable<List<GistHistoryServer>> getCommitsByGistId(String id) {
        return mGithubService.gist_commits(id).compose(RxUtils.async());
    }
}
