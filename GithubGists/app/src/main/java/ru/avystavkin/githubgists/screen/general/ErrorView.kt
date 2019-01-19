package ru.avystavkin.githubgists.screen.general

interface ErrorView : LoadingView {
    fun showError(throwable: Throwable)
    fun showNoAccessNetworkMessage(throwable: Throwable)
}
