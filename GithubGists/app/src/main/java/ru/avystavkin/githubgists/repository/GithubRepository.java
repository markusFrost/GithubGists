package ru.avystavkin.githubgists.repository;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.GistCommit;

public interface GithubRepository {

    @NonNull
    Observable<List<Gist>> getGists();

    @NonNull
    Observable<Gist> getGistById(String id);

    @NonNull
    Observable<List<Gist>> getGistsByUserName(String name);

    @NonNull
    Observable<List<GistCommit>> getCommitsByGistId(String id);

}
