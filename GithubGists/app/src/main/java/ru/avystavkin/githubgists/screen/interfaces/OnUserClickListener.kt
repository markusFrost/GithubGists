package ru.avystavkin.githubgists.screen.interfaces

import ru.avystavkin.githubgists.models.local.User

interface OnUserClickListener {
    fun onUserClick(user: User)
}
