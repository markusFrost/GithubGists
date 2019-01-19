package ru.avystavkin.githubgists.screen.gist_detail

import ru.avystavkin.githubgists.screen.general.ErrorView

interface GistView : ErrorView {

    override fun showError(throwable: Throwable)

    fun showGist(item: Any)
}