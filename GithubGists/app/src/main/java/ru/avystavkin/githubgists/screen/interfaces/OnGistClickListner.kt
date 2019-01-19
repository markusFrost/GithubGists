package ru.avystavkin.githubgists.screen.interfaces

import ru.avystavkin.githubgists.models.local.Gist

interface OnGistClickListner {
    fun onGistClick(gist : Gist)
}
