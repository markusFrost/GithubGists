package ru.avystavkin.githubgists.screen.interfaces

import ru.avystavkin.githubgists.models.database.Gist

interface OnGistClickListner {
    fun onGistClick(gist : Gist)
}
