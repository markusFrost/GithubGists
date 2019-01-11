package ru.avystavkin.githubgists.screen.base.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.avystavkin.githubgists.R;
import ru.avystavkin.githubgists.screen.general.LoadingDialog;
import ru.avystavkin.githubgists.screen.general.LoadingView;
import ru.avystavkin.githubgists.widget.DividerItemDecoration;
import ru.avystavkin.githubgists.widget.EmptyRecyclerView;

public abstract class BaseActivity extends AppCompatActivity {

    public static final String KEY_NAME = "key_name";
    public static final String KEY_ID = "key_url";
    public static final String KEY_USER_NAME = "key_user_name";
    public static final String KEY_USER_URL = "key_user_url";

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    public EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    public View mEmptyView;

    public LoadingView mLoadingView;

    public LifecycleHandler mLifecycleHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gists);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);

        mLifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
    }
}
