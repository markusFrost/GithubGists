package ru.avystavkin.githubgists.screen.main

import ru.avystavkin.githubgists.models.database.Gist
import ru.avystavkin.githubgists.models.database.User
import ru.avystavkin.githubgists.screen.general.ErrorView

interface MainPageView : ErrorView {

    fun showGists(gists: List<Gist>)

    fun showUsers(users: List<User>)
}
