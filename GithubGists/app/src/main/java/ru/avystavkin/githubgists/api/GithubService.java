package ru.avystavkin.githubgists.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.GistHistory;
import rx.Observable;

public interface GithubService {

    @GET("/gists/public")
    Observable<List<Gist>> gists();

    @GET("/gists/{gist_id}")
    Observable<Gist> gist_detail(@Path("gist_id") String id);

    @GET("/gists/{gist_id}/commits")
    Observable<List<GistHistory>> gist_commits(@Path("gist_id") String id);

}
