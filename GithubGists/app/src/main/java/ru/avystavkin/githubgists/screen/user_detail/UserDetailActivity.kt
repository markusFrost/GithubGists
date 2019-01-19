package ru.avystavkin.githubgists.screen.user_detail

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
import ru.avystavkin.githubgists.screen.interfaces.OnItemClickListener
import ru.avystavkin.githubgists.screen.main.gist.GistsAdapter
import java.util.*
import javax.inject.Inject

class UserDetailActivity : BaseActivity(), UserView, OnItemClickListener<Gist> {

    @Inject
    internal var mRepository: GithubRepository? = null

    private lateinit var mPresenter: UserDetailPresenter
    private lateinit var mAdapter: GistsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_gists)
        super.onCreate(savedInstanceState)

        mAdapter = GistsAdapter(ArrayList())
        mAdapter.attachToRecyclerView(mRecyclerView)
        mAdapter.onItemClickListener = this

        AppDelegate.appComponent.injectUserDetailActivity(this)

        mPresenter = UserDetailPresenter(mRepository!!, compositeDisposable, this)
        mPresenter.init(intent)
    }

    override fun showError(throwable: Throwable) {
        mAdapter.clear()
    }

    override fun showNoAccessNetworkMessage(throwable: Throwable) {
        Toast.makeText(this, resources.getString(R.string.no_network_access_message), Toast.LENGTH_SHORT).show()
    }

    override fun showGists(gists: List<Gist>) {
        mAdapter.changeDataSet(gists)
    }

    override fun showLoading() {
        mLoadingView.showLoading()
    }

    override fun hideLoading() {
        mLoadingView.hideLoading()
    }

    override fun onItemClick(gist: Gist) {
        GistDetailActivity.start(this, gist)
    }

    companion object {

        fun start(activity: Activity, user: User) {
            val intent = Intent(activity, UserDetailActivity::class.java)
            intent.putExtra(BaseActivity.KEY_ID, user.id)
            intent.putExtra(BaseActivity.KEY_USER_NAME, user.name)
            intent.putExtra(BaseActivity.KEY_USER_URL, user.url)
            activity.startActivity(intent)
        }
    }
}
