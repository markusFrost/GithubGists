package ru.avystavkin.githubgists.repository.github

import io.reactivex.Observable
import ru.avystavkin.githubgists.api.GithubService
import ru.avystavkin.githubgists.database.DbHelper
import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.models.local.GistCommit
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.repository.github.transformer.RxGistCommitTransformer
import ru.avystavkin.githubgists.repository.github.transformer.RxGistTransformer
import ru.avystavkin.githubgists.repository.github.transformer.RxGistsListTransformer
import ru.avystavkin.githubgists.utils.RxUtils

class DefaultGithubRepository(
        private val mDbHelper: DbHelper,
        private val mGithubService: GithubService) : GithubRepository {

    override val gists: Observable<List<Gist>>
        get() = mGithubService.gists()
                .compose(RxGistsListTransformer())
                .flatMap { gists ->
                    mDbHelper.insert(gists)
                    Observable.fromArray(gists)
                }
                .onErrorReturn { mDbHelper.getGists() }
                .compose(RxUtils.async())

    override fun getPopularUsers(count: Int): Observable<List<User>> {
        return Observable.just(true)
                .flatMap { x -> Observable.fromArray(mDbHelper.getPopularUsers(count)) }
                .compose(RxUtils.async())
    }

    override fun getGistById(id: String): Observable<Gist> {
        return mGithubService.gist_detail(id)
                .compose(RxGistTransformer())
                .compose(RxUtils.async())
    }

    override fun getGistsByUserName(name: String): Observable<List<Gist>> {
        return mGithubService.user_detail(name)
                .compose(RxGistsListTransformer())
                .compose(RxUtils.async())
    }

    override fun getCommitsByGistId(id: String): Observable<List<GistCommit>> {
        return mGithubService.gist_commits(id)
                .compose(RxGistCommitTransformer())
                .compose(RxUtils.async())
    }
}
