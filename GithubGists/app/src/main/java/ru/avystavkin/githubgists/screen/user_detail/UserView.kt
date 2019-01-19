package ru.avystavkin.githubgists.screen.user_detail

import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.screen.general.ErrorView

interface UserView : ErrorView {
    fun showGists(gists: List<Gist>)
}
