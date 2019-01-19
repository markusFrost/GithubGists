package ru.avystavkin.githubgists.screen.interfaces

import ru.avystavkin.githubgists.models.database.User

interface OnUserClickListener {
    fun onUserClick(user: User)
}
