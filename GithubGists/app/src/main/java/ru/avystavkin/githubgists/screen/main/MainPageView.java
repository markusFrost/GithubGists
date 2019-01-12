package ru.avystavkin.githubgists.screen.main;

import java.util.List;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.content.server.GistServer;
import ru.avystavkin.githubgists.content.server.UserServer;
import ru.avystavkin.githubgists.screen.general.LoadingView;

public interface MainPageView extends LoadingView {

    void showGists(@NonNull List<GistServer> gists);

    void showUsers(@NonNull List<UserServer> users);

    void showError();
}
