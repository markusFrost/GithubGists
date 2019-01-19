package ru.avystavkin.githubgists.screen.main

import ru.avystavkin.githubgists.models.local.Gist
import ru.avystavkin.githubgists.models.local.User
import ru.avystavkin.githubgists.screen.general.ErrorView

interface MainPageView : ErrorView {

    fun showGists(gists: List<Gist>)

    fun showUsers(users: List<User>)
}
