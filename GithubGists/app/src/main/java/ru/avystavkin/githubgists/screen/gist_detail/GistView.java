package ru.avystavkin.githubgists.screen.gist_detail;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.screen.general.LoadingView;

public interface GistView extends LoadingView {

    void showError(Throwable throwable);

    void showGist(@NonNull Object item);
}