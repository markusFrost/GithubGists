package ru.avystavkin.githubgists.repository;

import java.util.List;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;
import rx.Observable;

public interface GithubRepository {

    @NonNull
    Observable<List<Gist>> getGists();

    @NonNull
    Observable<Gist> getGistById(String id);

    @NonNull
    Observable<List<Gist>> getGistsByUserName(String name);

    @NonNull
    Observable<List<GistHistory>> getCommitsByGistId(String id);

}
