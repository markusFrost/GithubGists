package ru.avystavkin.githubgists.screen.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import ru.avystavkin.githubgists.AppDelegate
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.repository.github.GithubRepository
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity
import ru.avystavkin.githubgists.screen.gist_detail.GistDetailActivity
import ru.avystavkin.githubgists.screen.interfaces.OnMainPageClickListner
import ru.avystavkin.githubgists.screen.user_detail.UserDetailActivity
import javax.inject.Inject

class MainPageActivity : BaseActivity(), MainPageView, OnMainPageClickListner {

    private lateinit var mPresenter: MainPagePresenter

    @Inject
    internal lateinit var mRepository: GithubRepository

    private lateinit var mAdapter: MainPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_gists)
        super.onCreate(savedInstanceState)

        AppDelegate.appComponent.injectGistActivity(this)

        mAdapter = MainPageAdapter(this)
        mAdapter.attachToRecyclerView(mRecyclerView)

        mPresenter = MainPagePresenter(mRepository, compositeDisposable, this)
        mPresenter.init()
    }


    override fun showGists(gists: List<Gist>) {
        mAdapter.setListGists(gists)
    }

    override fun showUsers(users: List<User>) {
        mAdapter.setListUsers(users)
    }

    override fun showLoading() {
        mLoadingView.showLoading()
    }

    override fun hideLoading() {
        mLoadingView.hideLoading()
    }

    override fun showError(throwable: Throwable) {

    }

    override fun showNoAccessNetworkMessage(throwable: Throwable) {
        Toast.makeText(this, resources.getString(R.string.no_network_access_message), Toast.LENGTH_LONG).show()
    }

    override fun onGistClick(gist: Gist) {
        GistDetailActivity.start(this, gist)
    }

    override fun onUserClick(user: User) {
        UserDetailActivity.start(this, user)
    }

    companion object {

        fun start(activity: Activity) {
            val intent = Intent(activity, MainPageActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
