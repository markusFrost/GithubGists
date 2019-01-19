package ru.avystavkin.githubgists.screen.user_detail

import android.content.Intent
import io.reactivex.disposables.CompositeDisposable
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.repository.github.GithubRepository
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity

class UserDetailPresenter(private val mRepository: GithubRepository,
                          private val mCompositeDisposable: CompositeDisposable,
                          private val mView: UserView) {

    fun init(intent: Intent?) {
        if (intent == null)
            return

        val user = User()

        if (intent.hasExtra(BaseActivity.KEY_ID))
            user.id = intent.getLongExtra(BaseActivity.KEY_ID, -1)

        if (intent.hasExtra(BaseActivity.KEY_USER_NAME))
            user.name = intent.getStringExtra(BaseActivity.KEY_USER_NAME)

        if (intent.hasExtra(BaseActivity.KEY_USER_URL))
            user.url = intent.getStringExtra(BaseActivity.KEY_USER_URL)

        loadUserGists(user.name)
    }

    private fun loadUserGists(name: String?) {
        //        //---temp
        //        name = "name";
        //        //---temp

        val disposable = mRepository.getGistsByUserName(name!!)
                .doOnSubscribe { d -> mView.showLoading() }
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showGists, mView::showError)

        mCompositeDisposable.add(disposable)
    }
}
