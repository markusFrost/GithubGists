package ru.avystavkin.githubgists.screen.general;

public interface ErrorView extends LoadingView {
    void showError(Throwable throwable);
    void showNoAccessNetworkMessage(Throwable throwable);
}
