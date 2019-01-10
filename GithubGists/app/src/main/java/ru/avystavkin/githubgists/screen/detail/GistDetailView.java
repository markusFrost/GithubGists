package ru.avystavkin.githubgists.screen.detail;

import android.support.annotation.NonNull;

import ru.avystavkin.githubgists.screen.general.LoadingView;

public interface GistDetailView extends LoadingView {

    void showError(Throwable throwable);

    void showGist(@NonNull Object item);
}