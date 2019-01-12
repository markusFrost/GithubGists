package ru.avystavkin.githubgists.screen.main;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.repository.GithubRepository;

public class MainPagePresenter {

    private static final int POPULAR_COUNT = 10;

    private final GithubRepository mRepository;
    private final MainPageView mView;

    public MainPagePresenter(@NonNull GithubRepository repository,
                             @NonNull MainPageView view) {
        mRepository = repository;
        mView = view;
    }

    public void init() {
        mRepository.getGists()
                .doOnSubscribe(d -> mView.showLoading())
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showGists, throwable -> mView.showError());
    }

    public void loadUsers(@NonNull List<Gist> gists) {
       //will work after db implement
        List<User> list = new ArrayList<>();
        for (int i = 0 ; i <= POPULAR_COUNT; i++) {
            User user = gists.get(i).getUser();
            if (user != null) {
                list.add(user);
            }
            mView.showUsers(list);
        }

    }

    private User getUserByLogin(List<Gist> list, String login) {
        for (Gist gist : list) {
            User user = gist.getUser();
            if (user != null && user.getLogin().equals(login))
                return user;
        }
        return new User();
    }
}
