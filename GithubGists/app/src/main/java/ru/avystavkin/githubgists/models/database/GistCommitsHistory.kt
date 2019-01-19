package ru.avystavkin.githubgists.models.database

data class GistCommitsHistory (
        var total: Int,
        var additions: Int,
        var deletions: Int) {
    constructor() : this(0,0,0)
}

