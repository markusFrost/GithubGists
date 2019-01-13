package ru.avystavkin.githubgists.screen.main;

import java.util.List;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.models.local.User;
import ru.avystavkin.githubgists.screen.general.ErrorView;

public interface MainPageView extends ErrorView {

    void showGists(@NonNull List<Gist> gists);

    void showUsers(@NonNull List<User> users);
}
