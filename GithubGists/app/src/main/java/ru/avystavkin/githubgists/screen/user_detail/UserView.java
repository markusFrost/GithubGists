package ru.avystavkin.githubgists.screen.user_detail;

import java.util.List;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.models.local.Gist;
import ru.avystavkin.githubgists.screen.general.LoadingView;

public interface UserView extends LoadingView {
    void showError(Throwable throwable);
    void showGists(@NonNull List<Gist> gists);

}
