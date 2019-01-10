package ru.avystavkin.githubgists.screen.main;

import android.support.annotation.NonNull;

import java.util.List;

import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.content.User;
import ru.avystavkin.githubgists.screen.general.LoadingView;

public interface MainPageView extends LoadingView {

    void showGists(@NonNull List<Gist> gists);

    void showUsers(@NonNull List<User> users);

    void showError();
}
