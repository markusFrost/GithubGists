package ru.avystavkin.githubgists.models.local

data class GistCommit (
    var user: User?,
    var version: String?,
    var commitDateTime: String?,
    var commitsHistory: GistCommitsHistory?,
    var url: String?) {
    constructor() : this(null,"","",null,"")
}

