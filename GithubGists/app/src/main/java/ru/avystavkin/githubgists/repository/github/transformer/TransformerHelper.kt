package ru.avystavkin.githubgists.repository.github.transformer

import ru.avystavkin.githubgists.models.database.*
import ru.avystavkin.githubgists.models.server.GistCommitServer
import ru.avystavkin.githubgists.models.server.GistServer
import ru.avystavkin.githubgists.models.server.UserServer
import java.util.*

object TransformerHelper {

    fun convertGistCommitsHistory(commitServer: GistCommitServer): GistCommit {
        val commit = GistCommit()
        val history = GistCommitsHistory()

        val historyServer = commitServer.status
        if (historyServer != null) {
            history.additions = historyServer.additions
            history.deletions = historyServer.deletions
            history.total = historyServer.total
        }
        commit.user = convertUser(commitServer.user)
        commit.url = commitServer.url
        commit.commitDateTime = commitServer.committedAt
        commit.commitsHistory = history
        commit.version = commitServer.version

        return commit
    }

    private fun convertUser(userServer: UserServer?): User {
        val user = User()
        if (userServer != null) {
            user.url = userServer.avatarUrl
            user.id = userServer.id
            user.name = userServer.login
        }
        return user
    }

    fun convertGistServer(gistServer: GistServer): Gist {
        val gist = Gist()
        val user = convertUser(gistServer.user)

        gist.id = gistServer.id

        val list = ArrayList<GistFileInfo>()
        val size = gistServer.files?.size;
        if (size != null && size > 0) {
            for (key in gistServer.files.keys) {
                val fileInfoServer = gistServer.files[key] ?: continue
                val fileInfo = GistFileInfo()

                fileInfo.content = fileInfoServer.content
                fileInfo.fileName = fileInfoServer.fileName

                list.add(fileInfo)
            }
        }
        gist.listFiles = list
        gist.url = gistServer.url
        gist.name = if (gistServer.description.isNullOrEmpty()) "gist:" + gistServer.id!! else gistServer.description
        gist.user = user
        gist.userId = user.id

        return gist
    }
}
