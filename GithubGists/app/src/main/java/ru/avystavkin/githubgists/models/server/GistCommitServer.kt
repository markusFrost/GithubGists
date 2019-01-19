package ru.avystavkin.githubgists.models.server

import com.google.gson.annotations.SerializedName

class GistCommitServer {
    @SerializedName("user")
    var user: UserServer? = null

    @SerializedName("version")
    var version: String? = null

    @SerializedName("committed_at")
    var committedAt: String? = null

    @SerializedName("change_status")
    var status: GistCommitsHistoryServer? = null

    @SerializedName("url")
    var url: String? = null
}
