package ru.avystavkin.githubgists.repository;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import ru.avystavkin.githubgists.content.server.GistServer;
import ru.avystavkin.githubgists.content.server.GistHistoryServer;

public interface GithubRepository {

    @NonNull
    Observable<List<GistServer>> getGists();

    @NonNull
    Observable<GistServer> getGistById(String id);

    @NonNull
    Observable<List<GistServer>> getGistsByUserName(String name);

    @NonNull
    Observable<List<GistHistoryServer>> getCommitsByGistId(String id);

}
