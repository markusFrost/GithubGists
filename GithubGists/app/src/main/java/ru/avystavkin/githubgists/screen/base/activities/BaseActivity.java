package ru.avystavkin.githubgists.screen.base.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
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

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected Unbinder unbinder;

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    public EmptyRecyclerView mRecyclerView;

    @BindView(R.id.empty)
    public View mEmptyView;

    public LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gists);

        unbinder = ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        compositeDisposable.dispose();
    }
}
