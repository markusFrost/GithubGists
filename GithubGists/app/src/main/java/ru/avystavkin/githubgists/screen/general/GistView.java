package ru.avystavkin.githubgists.screen.general;

import android.support.annotation.NonNull;

public interface GistView extends LoadingView {

    void showError(Throwable throwable);

    void showGist(@NonNull Object item);
}