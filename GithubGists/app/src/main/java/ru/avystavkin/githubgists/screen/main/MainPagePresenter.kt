package ru.avystavkin.githubgists.screen.main

import io.reactivex.disposables.CompositeDisposable
import ru.avystavkin.githubgists.repository.github.GithubRepository

class MainPagePresenter(private val mRepository: GithubRepository,
                        private val mCompositeDisposable: CompositeDisposable,
                        private val mView: MainPageView) {

    private val POPULAR_COUNT = 10

    fun init() {
        val disposable = mRepository.gists
                .doOnSubscribe { mView::showLoading }
                .doOnError { mView::showError }
                .subscribe({ gists ->
                    mView.showGists(gists)
                    loadPopularUsers()
                },  mView::showError )

        mCompositeDisposable.add(disposable)
    }

    private fun loadPopularUsers() {
        val disposablePopularUsers = mRepository.getPopularUsers(POPULAR_COUNT)
                .doAfterTerminate { mView::hideLoading }
                .doOnError { mView::showError }
                .subscribe( mView::showUsers ,  mView::showError )
        mCompositeDisposable.add(disposablePopularUsers)
    }
}
