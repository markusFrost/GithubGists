package ru.avystavkin.githubgists.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.avystavkin.githubgists.models.server.GistCommitServer;
import ru.avystavkin.githubgists.models.server.GistServer;

public interface GithubService {

    @GET("/gists/public")
    Observable<List<GistServer>> gists();

    @GET("/gists/{gist_id}")
    Observable<GistServer> gist_detail(@Path("gist_id") String id);

    @GET("/gists/{gist_id}/commits")
    Observable<List<GistCommitServer>> gist_commits(@Path("gist_id") String id);

    @GET("/users/{name}/gists")
    Observable<List<GistServer>>  user_detail(@Path("name") String name);

}
