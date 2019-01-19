package ru.avystavkin.githubgists.repository.github

import io.reactivex.Observable
import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.models.local.GistCommit
import ru.avystavkin.githubgists.models.local.User

interface GithubRepository {

    val gists: Observable<List<Gist>>

    fun getPopularUsers(count: Int): Observable<List<User>>

    fun getGistById(id: String): Observable<Gist>

    fun getGistsByUserName(name: String): Observable<List<Gist>>

    fun getCommitsByGistId(id: String): Observable<List<GistCommit>>

}
