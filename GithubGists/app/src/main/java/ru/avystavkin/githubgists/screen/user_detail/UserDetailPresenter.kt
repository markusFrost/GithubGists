package ru.avystavkin.githubgists.screen.user_detail

import android.content.Intent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.repository.github.GithubRepository
import ru.avystavkin.githubgists.screen.gist_detail.GistDetailActivity

class UserDetailPresenter(private val mRepository: GithubRepository,
                          private val mCompositeDisposable: CompositeDisposable,
                          private val mView: UserView) {

    fun init(intent: Intent?) {
        if (intent == null)
            return

        val user = User()

        if (intent.hasExtra(GistDetailActivity.KEY_ID))
            user.id = intent.getLongExtra(GistDetailActivity.KEY_ID, -1)

        if (intent.hasExtra(GistDetailActivity.KEY_USER_NAME))
            user.name = intent.getStringExtra(GistDetailActivity.KEY_USER_NAME)

        if (intent.hasExtra(GistDetailActivity.KEY_USER_URL))
            user.url = intent.getStringExtra(GistDetailActivity.KEY_USER_URL)

        loadUserGists(user.name)
    }

    private fun loadUserGists(name: String?) {
        //        //---temp
        //        name = "name";
        //        //---temp

        val disposable = mRepository.getGistsByUserName(name!!)
                .doOnSubscribe { d -> mView.showLoading() }
                .doOnTerminate(Action { mView.hideLoading() })
                .subscribe(Consumer<List<Gist>> { mView.showGists(it) }, Consumer<Throwable> { mView.showError(it) })

        mCompositeDisposable.add(disposable)
    }
}
