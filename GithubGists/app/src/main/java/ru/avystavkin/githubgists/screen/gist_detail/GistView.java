package ru.avystavkin.githubgists.screen.gist_detail;

import androidx.annotation.NonNull;
import ru.avystavkin.githubgists.screen.general.ErrorView;

public interface GistView extends ErrorView {

    void showError(Throwable throwable);

    void showGist(@NonNull Object item);
}