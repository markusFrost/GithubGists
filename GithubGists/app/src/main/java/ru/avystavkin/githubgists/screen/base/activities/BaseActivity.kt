package ru.avystavkin.githubgists.screen.base.activities

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import io.reactivex.disposables.CompositeDisposable
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.screen.general.LoadingDialog
import ru.avystavkin.githubgists.screen.general.LoadingView
import ru.avystavkin.githubgists.widget.DividerItemDecoration
import ru.avystavkin.githubgists.widget.EmptyRecyclerView

abstract class BaseActivity : AppCompatActivity() {

    protected var compositeDisposable = CompositeDisposable()
    protected lateinit var unbinder: Unbinder

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    @BindView(R.id.recyclerView)
    lateinit var mRecyclerView: EmptyRecyclerView

    @BindView(R.id.empty)
    lateinit var mEmptyView: View

    lateinit var mLoadingView: LoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gists)

        unbinder = ButterKnife.bind(this)
        setSupportActionBar(mToolbar)

        mLoadingView = LoadingDialog.view(supportFragmentManager)

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.addItemDecoration(DividerItemDecoration(this))
        mRecyclerView.emptyView = mEmptyView
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
        compositeDisposable.dispose()
    }

    companion object {
//todo it is not init block
        val KEY_NAME = "key_name"
        val KEY_ID = "key_url"
        val KEY_USER_NAME = "key_user_name"
        val KEY_USER_URL = "key_user_url"
    }
}
