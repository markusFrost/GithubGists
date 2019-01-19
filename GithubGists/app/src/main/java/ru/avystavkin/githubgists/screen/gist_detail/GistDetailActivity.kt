package ru.avystavkin.githubgists.screen.gist_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import ru.avystavkin.githubgists.AppDelegate
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.models.local.GistCommit
import ru.avystavkin.githubgists.repository.github.GithubRepository
import ru.avystavkin.githubgists.screen.base.activities.BaseActivity
import javax.inject.Inject

class GistDetailActivity : BaseActivity(), GistView {

    private lateinit var mPresenter: GistDetailPresenter

    @Inject
    internal lateinit var mRepository: GithubRepository

    private lateinit var mAdapter: GistDetailAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_gists)
        super.onCreate(savedInstanceState)

        mAdapter = GistDetailAdapter()
        mAdapter.attachToRecyclerView(mRecyclerView)

        AppDelegate.appComponent.injectGistDetailActivity(this)

        mPresenter = GistDetailPresenter(mRepository, compositeDisposable, this)
        mPresenter.init(intent)
    }

    override fun showGist(item: Any) {
        if (item is Gist)
            mAdapter.setGist(item)
        else if (item is List<*>)
            mAdapter.setCommits(item as List<GistCommit>)
    }

    override fun showError(throwable: Throwable) {
        println(throwable.toString())
    }

    override fun showLoading() {
        mLoadingView.showLoading()
    }

    override fun hideLoading() {
        mLoadingView.hideLoading()
    }

    override fun showNoAccessNetworkMessage(throwable: Throwable) {
        Toast.makeText(this, resources.getString(R.string.no_network_access_message), Toast.LENGTH_SHORT).show()
    }

    companion object {

        fun start(activity: Activity, gist: Gist) {
            val intent = Intent(activity, GistDetailActivity::class.java)
            intent.putExtra(BaseActivity.KEY_NAME, gist.name)
            intent.putExtra(BaseActivity.KEY_ID, gist.id)
            intent.putExtra(BaseActivity.KEY_USER_NAME, gist.user!!.name)
            intent.putExtra(BaseActivity.KEY_USER_URL, gist.user!!.url)
            activity.startActivity(intent)
        }
    }
}
