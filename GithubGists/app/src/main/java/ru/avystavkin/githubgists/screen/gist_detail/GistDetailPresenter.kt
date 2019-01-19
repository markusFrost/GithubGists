package ru.avystavkin.githubgists.screen.gist_detail

import android.content.Intent
import android.text.TextUtils
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ru.avystavkin.githubgists.models.database.Gist
import ru.avystavkin.githubgists.models.database.User
import ru.avystavkin.githubgists.repository.github.GithubRepository
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity

class GistDetailPresenter(private val mRepository: GithubRepository,
                          private val mCompositeDisposable: CompositeDisposable,
                          private val mView: GistView) {

    fun init(intent: Intent?) {
        if (intent == null)
            return

        val gist = Gist()
        gist.user = User()

        if (intent.hasExtra(BaseActivity.KEY_NAME))
            gist.name = intent.getStringExtra(BaseActivity.KEY_NAME)

        if (intent.hasExtra(BaseActivity.KEY_ID))
            gist.id = intent.getStringExtra(BaseActivity.KEY_ID)

        if (intent.hasExtra(BaseActivity.KEY_USER_NAME))
            gist.user!!.name = intent.getStringExtra(BaseActivity.KEY_USER_NAME)

        if (intent.hasExtra(BaseActivity.KEY_USER_URL))
            gist.user!!.url = intent.getStringExtra(BaseActivity.KEY_USER_URL)

        mView.showGist(gist)
        init(gist.id)
    }

    private fun init(id: String?) {
        //        //---temp
        //        id = "id";
        //        //---temp
        if (TextUtils.isEmpty(id)) {
            return
        }

        val observableDetail = mRepository.getGistById(id!!)
        val observableCommits = mRepository.getCommitsByGistId(id)

        val disposable = Observable.merge(observableDetail, observableCommits)
                .doOnSubscribe { d -> mView.showLoading() }
                .doOnTerminate( mView::hideLoading)
                .subscribe(mView::showGist, mView::showError)

        mCompositeDisposable.add(disposable)
    }
}
