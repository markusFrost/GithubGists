package ru.avystavkin.githubgists.repository.github;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.GistCommit;
import ru.avystavkin.githubgists.models.local.User;

public interface GithubRepository {

    @NonNull
    Observable<List<Gist>> getGists();

    @NonNull
    Observable<List<User>> getPopularUsers(int count);

    @NonNull
    Observable<Gist> getGistById(String id);

    @NonNull
    Observable<List<Gist>> getGistsByUserName(String name);

    @NonNull
    Observable<List<GistCommit>> getCommitsByGistId(String id);

}
