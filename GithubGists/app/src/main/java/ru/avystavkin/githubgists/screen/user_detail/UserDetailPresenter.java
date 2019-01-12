package ru.avystavkin.githubgists.screen.user_detail;

import android.content.Intent;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.repository.github.GithubRepository;
import ru.avystavkin.githubgists.screen.gist_detail.GistDetailActivity;

public class UserDetailPresenter {

    private final GithubRepository mRepository;
    private final UserView mView;
    private final CompositeDisposable mCompositeDisposable;

    public UserDetailPresenter(@NonNull GithubRepository repository,
                               @NonNull CompositeDisposable compositeDisposable,
                               @NonNull UserView view) {
        mRepository = repository;
        mView = view;
        mCompositeDisposable = compositeDisposable;
    }

    public void init(Intent intent) {
        if (intent == null)
            return;

        User user = new User();

        if (intent.hasExtra(GistDetailActivity.KEY_ID))
            user.setId(intent.getStringExtra(GistDetailActivity.KEY_ID));

        if (intent.hasExtra(GistDetailActivity.KEY_USER_NAME))
            user.setName(intent.getStringExtra(GistDetailActivity.KEY_USER_NAME));

        if (intent.hasExtra(GistDetailActivity.KEY_USER_URL))
            user.setUrl(intent.getStringExtra(GistDetailActivity.KEY_USER_URL));

        loadUserGists(user.getName());
    }

    private void loadUserGists(String name) {
        //---temp
        name = "name";
        //---temp

      Disposable disposable = mRepository.getGistsByUserName(name)
                .doOnSubscribe(d-> mView.showLoading())
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showGists, mView::showError);

      mCompositeDisposable.add(disposable);
    }
}
