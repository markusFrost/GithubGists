package ru.avystavkin.githubgists.screen.user_detail;

import android.support.annotation.NonNull;

import java.util.List;

import ru.avystavkin.githubgists.content.Gist;
import ru.avystavkin.githubgists.screen.general.LoadingView;

public interface UserView extends LoadingView {
    void showError(Throwable throwable);
    void showGists(@NonNull List<Gist> gists);

}
