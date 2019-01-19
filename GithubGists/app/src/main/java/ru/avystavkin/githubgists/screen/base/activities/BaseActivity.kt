package ru.avystavkin.githubgists.screen.base.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_gists.*
import kotlinx.android.synthetic.main.empty_text_view.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.avystavkin.githubgists.R
import ru.avystavkin.githubgists.screen.general.LoadingDialog
import ru.avystavkin.githubgists.screen.general.LoadingView
import ru.avystavkin.githubgists.widget.DividerItemDecoration

abstract class BaseActivity : AppCompatActivity() {

    protected var compositeDisposable = CompositeDisposable()

    lateinit var loadingView: LoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gists)

        setSupportActionBar(toolbar)

        loadingView = LoadingDialog.view(supportFragmentManager)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this))
        recyclerView.emptyView = emptyView
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {
        val KEY_NAME = "key_name"
        val KEY_ID = "key_url"
        val KEY_USER_NAME = "key_user_name"
        val KEY_USER_URL = "key_user_url"
    }
}
