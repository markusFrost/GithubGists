package ru.avystavkin.githubgists.models.local

class GistCommit {
    var user: User? = null

    var version: String? = null

    var commitDateTime: String? = null

    var commitsHistory: GistCommitsHistory? = null

    var url: String? = null
}

