package ru.avystavkin.githubgists.screen.user_detail;

import android.content.Intent;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;
import ru.avystavkin.githubgists.screen.gist_detail.GistDetailActivity;

public class UserDetailPresenter {

    private final GithubRepository mRepository;
    private final UserView mView;

    public UserDetailPresenter(@NonNull GithubRepository repository,
                               @NonNull UserView view) {
        mRepository = repository;
        mView = view;
    }

    public void init(Intent intent) {
        if (intent == null)
            return;

        User user = new User();

        if (intent.hasExtra(GistDetailActivity.KEY_ID))
            user.setId(intent.getStringExtra(GistDetailActivity.KEY_ID));

        if (intent.hasExtra(GistDetailActivity.KEY_USER_NAME))
            user.setLogin(intent.getStringExtra(GistDetailActivity.KEY_USER_NAME));

        if (intent.hasExtra(GistDetailActivity.KEY_USER_URL))
            user.setAvatarUrl(intent.getStringExtra(GistDetailActivity.KEY_USER_URL));

        loadUserGists(user.getLogin());
    }

    private void loadUserGists(String name) {
        //---temp
        name = "name";
        //---temp

        mRepository.getGistsByUserName(name)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                //.compose(mLifecycleHandler.load(R.id.gists_request))
                .subscribe(mView::showGists, mView::showError);
    }
}
