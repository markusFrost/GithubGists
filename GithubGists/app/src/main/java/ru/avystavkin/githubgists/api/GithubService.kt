package ru.avystavkin.githubgists.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.avystavkin.githubgists.models.server.GistCommitServer
import ru.avystavkin.githubgists.models.server.GistServer

interface GithubService {

    @GET("/gists/public")
    fun gists(): Observable<List<GistServer>>

    @GET("/gists/{gist_id}")
    fun gist_detail(@Path("gist_id") id: String): Observable<GistServer>

    @GET("/gists/{gist_id}/commits")
    fun gist_commits(@Path("gist_id") id: String): Observable<List<GistCommitServer>>

    @GET("/users/{name}/gists")
    fun user_detail(@Path("name") name: String): Observable<List<GistServer>>

}
